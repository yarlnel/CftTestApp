# ___CftTestApp___ - Тестовое задание для компании ЦФТ 
## Используемые библиотеки:
  0) Retrofit 2
  0) Dagger 2
  0) Room Dao
  0) Gson 
  0) Всевозможные CallbackAdapter.Factory и GsonAdapter.Factory для Retrofit-а
  
  
      > ___Причины использования именно этих библиотек изложенны в сопроводитльном писме к проекту___
  
  
## Преимущества именно моего проекта:
  1) Четкая организация кода по пакетам
  2) Использование DI на полную катушку (Спасибо выученному недавно Dagger-у)
  3) Документация в markdown `:)`
  4) Более-менее читаемый код
    
    
## Структура проекта:
* __api__ пакет отвечающий за взаимодействие с внешними ресурсами (Через Retrofit):
  - `IntervalRequestToCBService` - класс отвечающий за периодические запросы к серверу центробанка
     + `private val intervalPeriod` - Период между запросами
     + `val service : Observable<ResponseFromCB>` -
          Метод возвращающий RxProducer(Observable) который\
          периодически делает запросы через  `CentralBankService().getValuteCurse()` 
     + `fun stopService ()` -  останавливает таймер чтобы не возникло утечки ресурсов
  - `CentralBankService` - интерфейс сервиса взаимодействия с api центробанка реализуемый ретрофитом
    + `fun getValuteCurse () : Single<ResponseFromCB>` - Метод возвращает текущий курс валют обернутый в Single
* __db__ пакет отвечающий за работу с Базой Данных
  - `MainDao` - DAO [(Data Access Object)](https://ru.wikipedia.org/wiki/Data_Access_Object) - Объект доступа к данным
    + `fun saveResponseFromCB (responseFromCB: ResponseFromCB)` - Сохраняет ответ от сервера в БД
    + `fun getAllResponseFromCB () : List<ResponseFromCB>` - Получает все объекты типа ResponseFromCB из БД
    + `fun getLastResponseFromCB () : ResponseFromCB` - Получает последний записанный в Базу Данных ответ от сервера центробанка
  - `MainDatabase` - класс БД всего приложения
  - `StringToValuteMapConvector` - Конвектор для маршалинга или анмаршалинга структуры вида `Map<String, Valute>`
* __pojo__ пакет с POJO (Ну скорее с kotlin data classes) для удобного анмаршалинга данных из json 
  - `ResponseFromCB` - pojo ответа от сервера
  - `Valute` - pojo для определенной валюты
* __ui__ пакет со всем что отвечает за UI
  - `MainActivity` - Главный экран приложения
  - `ValuteRecyclerViewAdapter` - Саммый раздутый класс в проекте, адаптер для RecyclerView 
    + ```kotlin
      // Init блок нашего адаптера
      init {
          cbService
              .service
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.io())
              .subscribe({
                         valuteList.clear()
                         valuteList.addAll(
                             it.valutes?.map { e -> e.value }
                             ?:
                             listOf(Valute()))
                         notifyDataSetChanged()
              },{})
              .let(compositeDisposable::add)
      }
      ```
     + `fun onActivityDestroy ()` - обнуляет данные дабы избежать утечки памяти
     + `fun setRubleSum (sum: Double)` - устанавливает сумму пользователя в рублях и реактивно обновляет все данные в RecyclerView
* __di__ пакет отвечающий за внедрение зависимостей
    - `AppComponent` - Главный DI Компонент
    - `AppModule` - Главный Модуль
    - `ContextModule` - Модуль отвечающий за предоставление контекста
    - `DataBaseModule` - Модуль Базы Данных
    - `GsonModule` - Модуль Gson
    - `RetrofitModule` - Модуль настройки Retrofit
    - `RxModule` - Модуль RxJava  (ничего не делает, авось пригодиться __(привет _YAGNI_ !!!)__)
    - `UiModule` - Модуль отвечающий за Ui 
* `App.kt` - главный класс Application нашего приложения
  
