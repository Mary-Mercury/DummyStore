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

В текущий момент идет доработка интерфейса, а также работа над реализацией авторизации в приложении.

# Некоторые скриншоты приложения

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/4648ca84-b570-49be-b80c-ef44654f52d8" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/1f2d024d-5330-472f-9dc5-770dc76a6253" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/e4efd6fe-0b3f-49f5-af06-9483549dbee6" />

<img width="19%" height="auto" alt="image" src="https://github.com/user-attachments/assets/32c4f3ae-a1b4-4e7d-98de-e05da5108f40" />
