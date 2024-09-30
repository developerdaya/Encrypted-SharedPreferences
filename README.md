# Encrypted-SharedPreferences
**Encrypted SharedPreferences** in Android provide a secure way to store key-value pairs of data in an encrypted format. This is particularly useful for storing sensitive information, such as user credentials or personal data, where security is a concern. Here’s how to use EncryptedSharedPreferences in an Android Kotlin application:

### 1. Add Dependencies

First, ensure that you have the necessary dependencies in your `build.gradle` file:

```groovy
dependencies {
    implementation "androidx.security:security-crypto:1.1.0-alpha03" // Check for the latest version
}
```

### 2. Initialize EncryptedSharedPreferences

You need to create an instance of `EncryptedSharedPreferences`. This can be done in your application class or wherever you need to use it.

```kotlin
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

val masterKeyAlias = MasterKey.Builder(context)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
    .build()

val sharedPreferences = EncryptedSharedPreferences.create(
    "my_encrypted_preferences",
    masterKeyAlias,
    context,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)
```

### 3. Store Data

You can store data just like you would with regular `SharedPreferences`:

```kotlin
val editor = sharedPreferences.edit()
editor.putString("username", "your_username")
editor.putString("password", "your_password")
editor.apply() // or editor.commit()
```

### 4. Retrieve Data

To retrieve the data, you can use the same methods as with regular `SharedPreferences`:

```kotlin
val username = sharedPreferences.getString("username", null)
val password = sharedPreferences.getString("password", null)
```

### 5. Remove Data

If you need to remove any stored data:

```kotlin
val editor = sharedPreferences.edit()
editor.remove("username")
editor.apply()
```

### 6. Benefits of EncryptedSharedPreferences

- **Security**: Data is encrypted using strong encryption algorithms, ensuring that sensitive information is protected.
- **Simplicity**: The API is straightforward and easy to integrate into existing applications.
- **Automatic Key Management**: The library handles the generation and storage of encryption keys.

### Example

Here is a simple example of using EncryptedSharedPreferences in an Android activity:

```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val masterKeyAlias = MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            "my_encrypted_preferences",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        // Example usage
        val editor = sharedPreferences.edit()
        editor.putString("username", "your_username")
        editor.apply()

        val username = sharedPreferences.getString("username", null)
        println("Username: $username")
    }
}
```
