# DummyStore
Приложение с поражающим ассортиментом товаров, от помады до Dodge Durango самой жирной комплектации! А если серьзено, то это простое тренировочное приложение которое хранит под капотом API сайта [dummyJson.com](https://dummyjson.com).

# Стек инструментов и технологий
При разработке приложения использовались следующие библиотеки, плагины и паттерны:
  1. MVVM
  2. Hilt
  3. Retrofit
  4. Gson
  5. Coil

Итоговый набор инструментов может расшириться, смотрите по обновлениям.

# Запросы по API

Текущий список запросов весьма скудный:
  1. Выборка всех товаров
  ```
  @GET("products?limit=0")
  suspend fun getAllProducts(): Products
  ```
  2. Выборка конкретного товара
  ```
  @GET("products/{id}")
  suspend fun getProductById(@Path("id") id: Int): Product
  ```
  3. Выборка категорий товаров
  ```
  @GET("products/category-list")
  suspend fun getProductCategories(): List<String>
  ```
  4. Выборка товаров по категории
  ```
  @GET("products/category/{categoryName}")
  suspend fun getProductsByCategory(@Path("categoryName") category: String): Products
  ```

# Развитие приложения

В текущий момент идет работа над реализацией авторизации в приложении.

# Некоторые скриншоты приложения

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/e326c7d5-900e-4a90-a6d3-aeeeba292a16" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/c9e7f221-ab14-4fae-8453-4dbc58d134ea" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/d8ed3abf-05fb-4360-aee9-ada4dbd42a10" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/c8ac41c1-0187-42d8-a737-68a5d4156998" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/cab9620c-979e-4f70-be2e-7e21c9d80232" />
