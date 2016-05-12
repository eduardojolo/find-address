#find-address#
    Find-address is a microservice that provides a performatic, robust and resilient way to search for an address given a postal code. It is meant to be a simple way to work with many external providers as it has no database but consumes REST APIs that have the necessary information.
    It continuously verifies the best REST provider to use based on the availability and response time. It also has an internal cache instance to avoid unnecessary requests and achieve better perfomance.

---

#Architecture#
Find-address project is a MVC microservice so the requests are processed by the following path:

###Generic Classes

+ *FindAddressController*:
  Responsible for receiving all the requests for an address based on a postal code for the supported countries.

+ *FacadeFindAddressImpl*:
  It is the facade for the different router implementations.

+ *AbstractFindAddressRouter*:
  This abstract class is the heart of this microservice. It is the responsible for orchestrate the different IFindAddressService implementations. It is also responsible for checking if a provider was not capable of finding an address and to start the performance analyzer.

+ *AbstractFindAddressService*:
  This abstract service has the generic implementation of the find address in the local cache instance, by using the `HazelcastFindAddressUtils` plus calls the specific implementation of the method `findAddressByPostalCodeIntegration`.
  
+ *HazelcastFindAddressUtils*:
  Controls the local cache instance and the multiple maps that store the addresses information by its postal code.

+ *IntegrationAnalyzerServiceImpl*:
  Provides the method `observeOrderAddressProviders` which returns an `Observable<Queue<IFindAddressService>>`. This is responsible for analising the performance of the, possible, multiple options of integration web services for the same country.

+ *AddressDTO*:
  This is the DTO returned by the default implementations. It contains the most simple address informations.

###Custom Implementations###

+ *Custom AddressDTO*:
  For example the `CorreiosapiAddressDTO` class, it extends the `AddressDTO`, but remaps the attributes by annotating the setters with the `@JsonProperty`.

+ *Custom AbstractFindAddressRouter*
  This abstract class implements the method `fillFindAddressServices` adding all the custom `AbstractFindAddressService` to the `findAddressServices` list. A good example of an extension is the class `FindAddressRouterBrazilImpl`, it implements methods required by the board, which we will discuss later, such as: `getCountryName`, `getCountryImageName()` and `getCallsCount`. Attention to the method `getCountryImageName()` which must return a valid image name with the extension and the image must be inside the `src/main/resources/static/img` folder.
  
+ *Custom AbstractFindAddressService*:
  This abstract class is necessary if you want to to use a cache map other than the default, provided by the method `getCacheMapName` in the `AbstractFindAddressService`. Also it is a good option to implement the method `getPostalCodeForPerformanceAnalyzer` here, for an example consider the `AbstractBrazilFindAddressService` class.
This should be extended, as seen in the `CorreiosFindAddressServiceImpl` to add a specific implementation for the methods `findAddressByPostalCodeIntegration` and `getWebServiceName`.

---

#Board#
    The board is a page with information about the current state of each country router and services implementation.
    
+ *BoardController*:
This controller has a single method that obtains the information to be displayed in the board page, by calling his specific service, adds it to the model and returns.

+ *BoardServiceDTO*:
DTO with the information of a single country find-address service and router.

+ *BoardServiceImpl*:
This class must contain an instance of each custom router implementation in the `routers` list because that is how the method `getBoardInfo()` is going to access the necessary information and return a list of `BoardServiceDTO`.

+ *board.html*:
This is a simple page that renders multiple boxes, one for each custom router implementation, with a small group of informations related to the current status of that router/service. Attention here as the image to be displayed under the country name must be inside the folder `src/main/resources/static/img` and its name and extension correctly spelled in the extension of the `AbstractFindAddressRouter` class.

---

#How to add new content#
    To introduce a new country in the find-address microservice the following steps should be followed.

+ The DTO:
You should determine if the AddressDTO would be enough for the new country services and implement a new extension of this DTO for each address web service provider.

+ The Facade:
It is necessary to add the new router in the `FacadeFindAddressImpl` so it will be visible by the `FindAddressController`. A new method should also be added here redirecting to the new `routeFindAddressByPostalCode(postalCode)`.

+ The Controller:
A new `@RequestMapping` must be added to the `FindAddressController` to expose the new country find-address implementation. Consider following the pattern /address/(country)/{postalCode}.

+ The AbstractFindAddressRouter:
A new router must be implemented for the new country and all the new implementations of the interface `IFindAddressService` added to the `findAddressServices` list. Be sure to also implemente the methods required by the board service.

+ The AbstractFindAddressService:
Extends the `AbstractFindAddressService` providing a new cache map name and postal code to be used in the performance tests.

+ The extension of the new AbstractFindAddressService:
For each new web service provider add a class that extends the new custom `AbstractFindAddressService`, done in the last step. Those must implement the method `findAddressByPostalCodeIntegration(String postalCode)` calling the provider.

    If everything was done correctly now you should have available a new find-address provider for a new country. It would be automaticaly added to the board and switch between the available providers to achieve the best performance.