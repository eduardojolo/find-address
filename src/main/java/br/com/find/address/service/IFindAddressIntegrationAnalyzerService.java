package br.com.find.address.service;

import java.util.Queue;

import rx.Observable;

public interface IFindAddressIntegrationAnalyzerService {

	Observable<Queue<IFindAddressService>> observeOrderAddressProviders(Queue<IFindAddressService> postalCodeServices);
}
