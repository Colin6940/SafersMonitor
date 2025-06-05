package com.samsung.health.hrdatatransfer.presentation;

import com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase;
import com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase;
import com.samsung.health.hrdatatransfer.domain.SendMessageUseCase;
import com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase;
import com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<MakeConnectionToHealthTrackingServiceUseCase> makeConnectionToHealthTrackingServiceUseCaseProvider;

  private final Provider<SendMessageUseCase> sendMessageUseCaseProvider;

  private final Provider<StopTrackingUseCase> stopTrackingUseCaseProvider;

  private final Provider<AreTrackingCapabilitiesAvailableUseCase> areTrackingCapabilitiesAvailableUseCaseProvider;

  private final Provider<TrackHeartRateUseCase> trackHeartRateUseCaseProvider;

  public MainViewModel_Factory(
      Provider<MakeConnectionToHealthTrackingServiceUseCase> makeConnectionToHealthTrackingServiceUseCaseProvider,
      Provider<SendMessageUseCase> sendMessageUseCaseProvider,
      Provider<StopTrackingUseCase> stopTrackingUseCaseProvider,
      Provider<AreTrackingCapabilitiesAvailableUseCase> areTrackingCapabilitiesAvailableUseCaseProvider,
      Provider<TrackHeartRateUseCase> trackHeartRateUseCaseProvider) {
    this.makeConnectionToHealthTrackingServiceUseCaseProvider = makeConnectionToHealthTrackingServiceUseCaseProvider;
    this.sendMessageUseCaseProvider = sendMessageUseCaseProvider;
    this.stopTrackingUseCaseProvider = stopTrackingUseCaseProvider;
    this.areTrackingCapabilitiesAvailableUseCaseProvider = areTrackingCapabilitiesAvailableUseCaseProvider;
    this.trackHeartRateUseCaseProvider = trackHeartRateUseCaseProvider;
  }

  @Override
  public MainViewModel get() {
    MainViewModel instance = newInstance(makeConnectionToHealthTrackingServiceUseCaseProvider.get(), sendMessageUseCaseProvider.get(), stopTrackingUseCaseProvider.get(), areTrackingCapabilitiesAvailableUseCaseProvider.get());
    MainViewModel_MembersInjector.injectTrackHeartRateUseCase(instance, trackHeartRateUseCaseProvider.get());
    return instance;
  }

  public static MainViewModel_Factory create(
      Provider<MakeConnectionToHealthTrackingServiceUseCase> makeConnectionToHealthTrackingServiceUseCaseProvider,
      Provider<SendMessageUseCase> sendMessageUseCaseProvider,
      Provider<StopTrackingUseCase> stopTrackingUseCaseProvider,
      Provider<AreTrackingCapabilitiesAvailableUseCase> areTrackingCapabilitiesAvailableUseCaseProvider,
      Provider<TrackHeartRateUseCase> trackHeartRateUseCaseProvider) {
    return new MainViewModel_Factory(makeConnectionToHealthTrackingServiceUseCaseProvider, sendMessageUseCaseProvider, stopTrackingUseCaseProvider, areTrackingCapabilitiesAvailableUseCaseProvider, trackHeartRateUseCaseProvider);
  }

  public static MainViewModel newInstance(
      MakeConnectionToHealthTrackingServiceUseCase makeConnectionToHealthTrackingServiceUseCase,
      SendMessageUseCase sendMessageUseCase, StopTrackingUseCase stopTrackingUseCase,
      AreTrackingCapabilitiesAvailableUseCase areTrackingCapabilitiesAvailableUseCase) {
    return new MainViewModel(makeConnectionToHealthTrackingServiceUseCase, sendMessageUseCase, stopTrackingUseCase, areTrackingCapabilitiesAvailableUseCase);
  }
}
