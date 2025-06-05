package com.samsung.health.mobile.data;

import com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DataListenerService_MembersInjector implements MembersInjector<DataListenerService> {
  private final Provider<FirebaseHeartRateRepository> firebaseHeartRateRepositoryProvider;

  private final Provider<UserManager> userManagerProvider;

  public DataListenerService_MembersInjector(
      Provider<FirebaseHeartRateRepository> firebaseHeartRateRepositoryProvider,
      Provider<UserManager> userManagerProvider) {
    this.firebaseHeartRateRepositoryProvider = firebaseHeartRateRepositoryProvider;
    this.userManagerProvider = userManagerProvider;
  }

  public static MembersInjector<DataListenerService> create(
      Provider<FirebaseHeartRateRepository> firebaseHeartRateRepositoryProvider,
      Provider<UserManager> userManagerProvider) {
    return new DataListenerService_MembersInjector(firebaseHeartRateRepositoryProvider, userManagerProvider);
  }

  @Override
  public void injectMembers(DataListenerService instance) {
    injectFirebaseHeartRateRepository(instance, firebaseHeartRateRepositoryProvider.get());
    injectUserManager(instance, userManagerProvider.get());
  }

  @InjectedFieldSignature("com.samsung.health.mobile.data.DataListenerService.firebaseHeartRateRepository")
  public static void injectFirebaseHeartRateRepository(DataListenerService instance,
      FirebaseHeartRateRepository firebaseHeartRateRepository) {
    instance.firebaseHeartRateRepository = firebaseHeartRateRepository;
  }

  @InjectedFieldSignature("com.samsung.health.mobile.data.DataListenerService.userManager")
  public static void injectUserManager(DataListenerService instance, UserManager userManager) {
    instance.userManager = userManager;
  }
}
