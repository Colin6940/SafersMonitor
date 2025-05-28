package com.samsung.health.mobile.data;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UserManager_Factory implements Factory<UserManager> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public UserManager_Factory(Provider<SharedPreferences> sharedPreferencesProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public UserManager get() {
    return newInstance(sharedPreferencesProvider.get());
  }

  public static UserManager_Factory create(Provider<SharedPreferences> sharedPreferencesProvider) {
    return new UserManager_Factory(sharedPreferencesProvider);
  }

  public static UserManager newInstance(SharedPreferences sharedPreferences) {
    return new UserManager(sharedPreferences);
  }
}
