package com.samsung.health.hrdatatransfer.domain;

import com.samsung.health.hrdatatransfer.data.CapabilityRepository;
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
public final class GetCapableNodes_Factory implements Factory<GetCapableNodes> {
  private final Provider<CapabilityRepository> capabilityRepositoryProvider;

  public GetCapableNodes_Factory(Provider<CapabilityRepository> capabilityRepositoryProvider) {
    this.capabilityRepositoryProvider = capabilityRepositoryProvider;
  }

  @Override
  public GetCapableNodes get() {
    return newInstance(capabilityRepositoryProvider.get());
  }

  public static GetCapableNodes_Factory create(
      Provider<CapabilityRepository> capabilityRepositoryProvider) {
    return new GetCapableNodes_Factory(capabilityRepositoryProvider);
  }

  public static GetCapableNodes newInstance(CapabilityRepository capabilityRepository) {
    return new GetCapableNodes(capabilityRepository);
  }
}
