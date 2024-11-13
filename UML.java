@startuml
class Admin {
    - categories: Categories
    - housingTypes: HousingTypes
    - services: Services
    - bookingSystem: BookingSystem
    - housings: Set<Housing>

    + addCategory(category: Category): void
    + addHousingType(housing: HousingType): void
    + addService(service: Service): void
    + getServices(): List<Service>
    + addHousing(housing: Housing): void
    + getTopTenTenants(): List<Tenant>
    + getFreeHousings(): Set<Housing>
    + occupationRate(): double
    - busyHousings(): Set<Housing>
}

Admin --> Categories :categories
Admin --> HousingTypes :housingTypes
Admin --> Services :services
Admin --> BookingSystem :bookingSystem
Admin --> Housing :housings

class BookingSystem implements BookingAcceptedObserver{
    -confirmedBookings : Set<Booking>
    -conditionalBookings : Map<Housing, Queue<Booking>>
    +bookingCancelledObservers : List<BookingCancelledObserver>
    +bookingAcceptedObservers : List<BookingAcceptedObserver>

    +notifyBookingAccepted(booking:Booking) : void
    +processBooking( booking:Booking) : void
    -isHousingAvailable( booking:Booking) : boolean
    +cancelBooking( booking:Booking) : void
    -notifyObserversBookingAccepted( booking:Booking) : void
    +getAllBookings( tenant:Tenant) : List<Booking>
    +getAllBookings() : List<Booking>
    +getFutureBookings( tenant:Tenant) : List<Booking>
    +getBookingsFromCity( tenant:Tenant,  city:City) : List<Booking>
    +getBookingCities( tenant:Tenant) :  List<City>
}

BookingSystem  -->  Booking : confirmedBookings
BookingSystem  -->  Booking : conditionalBookings
BookingSystem  -->  BookingCancelledObserver : bookingCancelledObservers
BookingSystem  -->  Housing : conditionalBookings
BookingSystem  -->  BookingAcceptedObserver : bookingAcceptedObservers

class Address {
    - country: Country
    - city: City
    - houseLocation: String

    + getHouseLocation(): String
    + getCity(): City
    + getCountry(): Country
}
Address --> Country:country
Address --> City:city

class Booking {
    - housing: Housing
    - tenant: Tenant
    - range: DateRange
    - paymentMethod: PaymentMethod
    - bookingObservers: List<BookingAcceptedObserver>

    + Booking(housing: Housing, tenant: Tenant, range: DateRange, paymentMethod: PaymentMethod)
    + getTenant(): Tenant
    + getHousing(): Housing
    + getOwner(): Owner
    + getRange(): DateRange
    + addObserver(observer: BookingAcceptedObserver): void
    + removeObserver(observer: BookingAcceptedObserver): void
    + checkOut(rankings: List<Ranking>): void
    + startsAfter(date: LocalDate): boolean
    + isOnCity(city: City): boolean
    + getCity(): City
    + isBookedOn(date: LocalDate): boolean
    + cancelBook(): void
    + acceptBook(): void
}

Booking --> Housing : housing
Booking --> Tenant : tenant
Booking --> DateRange : range
Booking --> PaymentMethod : paymentMethod
Booking --> BookingAcceptedObserver : bookingObservers

abstract class CancellationPolicy {
    - housing: Housing

    + CancellationPolicy(housing: Housing)
    + getHousing(): Housing
    + getCancellationFee(range: DateRange): double
}

CancellationPolicy --> Housing : housing

class Category {
    - name: String

    + Category(name: String)
    + getName(): String
}

class CategoryScore {
    - category: Category
    - score: int

    + CategoryScore(category: Category, score: int)
    + getCategory(): Category
    + getScore(): int
}

CategoryScore --> Category : category

class City {
    - name: String

    + City(name: String)
    + getName(): String
}

class Country {
    - name: String

    + Country(name: String)
    + getName(): String
}

class DateRange {
    - start: LocalDate
    - end: LocalDate

    + DateRange(start: LocalDate, end: LocalDate)
    + getStart(): LocalDate
    + getEnd(): LocalDate
    + getOverlapDays(other: DateRange): long
    + overlaps(other: DateRange): boolean
    + startsAfter(date: LocalDate): boolean
    + contains(date: LocalDate): boolean
}


class Housing implements Rankeable {
    - housingType: HousingType
    - area: float
    - address: Address
    - services: List<Service>
    - capacity: int
    - pictures: List<Picture>
    - stayDetails: HousingStayDetails
    - paymentMethods: List<PaymentMethod>
    - defaultPaymentMethod: PaymentMethod
    - priceCalculator: PriceCalculatorInterface
    - rankings: List<Ranking>
    - owner: Owner
    - cancellationPolicy: CancellationPolicy

    + Housing(housingType: HousingType, area: float, address: Address, services: List<Service>, 
              capacity: int, pictures: List<Picture>, stayDetails: HousingStayDetails, 
              paymentMethods: List<PaymentMethod>, priceCalculator: PriceCalculatorInterface, 
              owner: Owner, cancellationPolicy: CancellationPolicy)
    + getPrice(range: DateRange): double
    + getCapacity(): int
    + getAddress(): Address
    + isLocatedIn(city: City): boolean
    + isAvailable(dateRange: DateRange): boolean
    + getOwner(): Owner
    + getRankings(): List<Ranking>
    + addRanking(ranking: Ranking): void
    + getCity(): City
    + getCancelationFee(range: DateRange): double
    + getDefaultPaymentMethod(): PaymentMethod
}

Housing --> HousingType : housingType
Housing --> Address : address
Housing --> Service : services
Housing --> Picture : pictures
Housing --> HousingStayDetails : stayDetails
Housing --> PaymentMethod : paymentMethods
Housing --> PaymentMethod : defaultPaymentMethod
Housing --> PriceCalculatorInterface : priceCalculator
Housing --> Ranking : rankings
Housing --> Owner : owner
Housing --> CancellationPolicy : cancellationPolicy

class HousingStayDetails {
    - checkIn: LocalDateTime
    - checkOut: LocalDateTime

    + HousingStayDetails(checkIn: LocalDateTime, checkOut: LocalDateTime)
    + getCheckIn(): LocalDateTime
    + getCheckOut(): LocalDateTime
}

class HousingType {
    - name: String

    + HousingType(name: String)
    + getName(): String
}

class Owner extends User implements Rankeable, Ranker{
    - rankings: List<Ranking>
    - rentals: List<Booking>
    - bookingAcceptanceStrategy: BookingAcceptanceStrategy

    + Owner(fullName: String, email: String, phoneNumber: String, createdOn: LocalDateTime, bookingAcceptanceStrategy: BookingAcceptanceStrategy)
    + addRental(rental: Booking): void
    + getRentals(): List<Booking>
    + addRanking(ranking: Ranking): void
    + getRankings(): List<Ranking>
    + rank(ranking: Ranking): void
    + accept(booking: Booking): void
    + cancelBook(booking: Booking): void
}

Owner -->Ranking:rankings
Owner -->Booking:rentals
Owner -->BookingAcceptanceStrategy:bookingAcceptanceStrategy

class Picture {
    - name: String
    - resourceUrl: String

    + Picture(name: String, resourceUrl: String)
    + getName(): String
    + getResourceUrl(): String
}

class PriceForRange {
    - price: double
    - range: DateRange

    + PriceForRange(price: double, range: DateRange)
    + getPriceForRange(range: DateRange): double
}

PriceForRange --> DateRange: range

class Ranking {
    - ranker: Ranker
    - ranked: Rankeable
    - categoryScores: List<CategoryScore>
    - comment: String
    - rankedOn: LocalDate

    + Ranking(ranker: Ranker, ranked: Rankeable, comment: String, categoryScores: List<CategoryScore>)
    + getScores(): List<CategoryScore>
    + getComment(): String
    + getRanker(): Ranker
    + getRanked(): Rankeable
    + getRankedOn(): LocalDate
}

Ranking --> Ranker:ranker
Ranking --> Rankeable:ranked
Ranking --> CategoryScore:categoryScores

class Tenant extends User implements Rankeable, Ranker{
    - rankings: List<Ranking>

    + Tenant(fullName: String, email: String, phoneNumber: String, createdOn: LocalDateTime)
    + addRanking(ranking: Ranking): void
    + getRankings(): List<Ranking>
    + rank(ranking: Ranking): void
    + book(housing: Housing, range: DateRange, paymentMethod: PaymentMethod, observers: List<BookingAcceptedObserver>): void
}

Tenant --> Ranking:rankings

class User {
    - fullName: String
    - email: String
    - phoneNumber: String
    - createdOn: LocalDateTime

    + User(fullName: String, email: String, phoneNumber: String, createdOn: LocalDateTime)
    + getFullName(): String
    + getEmail(): String
    + getPhoneNumber(): String
    + getCreatedOn(): LocalDateTime
}

class BookingConditionalStrategy implements BookingStrategy{
    - conditionalBookings: Map<Housing, Queue<Booking>>

    + BookingConditionalStrategy(conditionalBookings: Map<Housing, Queue<Booking>>)
    + process(booking: Booking): void
}

BookingConditionalStrategy --> Booking :conditionalBookings
BookingConditionalStrategy --> Housing :conditionalBookings

class BookingConfirmedStrategy implements BookingStrategy{
    - confirmedBookings: Set<Booking>

    + BookingConfirmedStrategy(confirmedBookings: Set<Booking>)
    + process(booking: Booking): void
}

confirmedBookings --> Booking: confirmedBookings

class CancellationEmailSender implements BookingCancelledObserver{
    - emailSender: EmailSender

    + CancellationEmailSender(emailSender: EmailSender)
    + notifyBookingCancelled(booking: Booking): void
}

CancellationEmailSender --> EmailSender:emailSender

class Cash  implements PaymentMethod{
    + applyCharge(amount: double): void
    + receivePayment(amount: double): void
}

class ConfirmationEmailSender  implements BookingAcceptedObserver{
    - emailSender: EmailSender
    + notifyBookingAccepted(booking: Booking): void
}

ConfirmationEmailSender --> EmailSender:emailSender

class CreditCard implements PaymentMethod{
    + applyCharge(amount: double): void
    + receivePayment(amount: double): void
}

class DebitCard implements PaymentMethod {
    + applyCharge(amount: double): void
    + receivePayment(amount: double): void
}

class EventPublisherImpl implements EventPublisher{
    - priceDropSubscribers: List<PriceDropSubscriber>
    - reservationCancelledSubscribers: List<ReservationCancelledSubscriber>
    - reservationAcceptedSubscribers: List<ReservationAcceptedSubscriber>

    + subscribeToPriceDrop(subscriber: PriceDropSubscriber): void
    + unsubscribeFromPriceDrop(subscriber: PriceDropSubscriber): void
    + subscribeToReservationCancelled(subscriber: ReservationCancelledSubscriber): void
    + unsubscribeFromReservationCancelled(subscriber: ReservationCancelledSubscriber): void
    + subscribeToReservationAccepted(subscriber: ReservationAcceptedSubscriber): void
    + unsubscribeFromReservationAccepted(subscriber: ReservationAcceptedSubscriber): void
    + notifyPriceDrop(propertyType: String, newPrice: double): void
    + notifyReservationCancelled(propertyType: String): void
    + notifyReservationAccepted(booking: Booking): void
}

EventPublisherImpl --> PriceDropSubscriber:priceDropSubscribers
EventPublisherImpl --> ReservationCancelledSubscriber:reservationCancelledSubscribers
EventPublisherImpl --> ReservationAcceptedSubscriber:reservationAcceptedSubscribers

class FreeCancellation extends CancellationPolicy{
    - MAX_DAYS_FOR_FREE_CANCELLATION: int

    + getCancellationFee(range: DateRange): double
    - twoDaysRange(range: DateRange): DateRange
}

class IntermediateCancellation extends CancellationPolicy{
    + getCancellationFee(range: DateRange): double
}

class NoCancellation extends CancellationPolicy{
    + getCancellationFee(range: DateRange): double
}

class PriceCalculatorImpl implements PriceCalculatorInterface{
    - priceForRanges: List<PriceForRange>

    + getPrice(range: DateRange): double
}

PriceCalculatorImpl-->PriceForRange:priceForRanges

class CapacityFilter implements SearchFilter{
    - capacity: int

    + filter(housingList: List<Housing>): List<Housing>
}

class CityFilter implements SearchFilter{
    - city: City

    + filter(housingList: List<Housing>): List<Housing>
}

class DateRangeFilter implements SearchFilter{
    - dateRange: DateRange

    + filter(housingList: List<Housing>): List<Housing>
}

DateRangeFilter --> DateRange:dateRange

class HousingSearch implements SearchFilter{
    - filterList: List<SearchFilter>

    + filter(housingList: List<Housing>): List<Housing>
}

HousingSearch-->SearchFilter:filterList


class MinPriceFilter implements SearchFilter{
    - minPrice: double
    - dateRange: DateRange
    
    + filter(housingList: List<Housing>): List<Housing>
}

MinPriceFilter --> DateRange:dateRange

class MaxPriceFilter implements SearchFilter{
    - maxPrice: double
    - dateRange: DateRange
    
    + filter(housingList: List<Housing>): List<Housing>
}

MaxPriceFilter --> DateRange:dateRange

class HousingSearchBuilder {
    - filterList: List<HousingSearchBuilder>
    - dateRange: DateRange

    + HousingSearchBuilder(city: City, dateRange: DateRange)
    + setCapacity(capacity: int): HousingSearchBuilder
    + setMinPrice(minPrice: double): HousingSearchBuilder
    + setMaxPrice(maxPrice: double): HousingSearchBuilder
    + build(): SearchFilter
}

HousingSearchBuilder --> HousingSearchBuilder:filterList
HousingSearchBuilder --> DateRange: dateRange

class CategoryAverageView {
    - average: double
    - category: Category

    + CategoryAverageView(average: double, category: Category)
    + getAverage(): double
    + getCategory(): Category
}

CategoryAverageView -->Category: category

class CommentView {
    - text: String
    - commentedOn: LocalDate
    - by: Ranker
    - scores: List<CategoryScore>

    + CommentView(text: String, commentedOn: LocalDate, by: Ranker, scores: List<CategoryScore>)
    + fromRanking(ranking: Ranking): CommentView
    + getText(): String
    + getCommentedOn(): LocalDate
    + getBy(): Ranker
    + getScores(): List<CategoryScore>
}

CommentView --> Ranker: by
CommentView --> CategoryScore:scores

class HousingView {
    - housing: Housing

    + HousingView(housing: Housing)
    + comments(): List<CommentView>
    + categoryAverages(): List<CategoryAverageView>
    + ownerDetails(): OwnerView
}

HousingView -->Housing: housing

@startuml
class OwnerRentalView {
    - owner: Owner
    - housing: Housing

    + OwnerRentalView(owner: Owner, currentHousing: Housing)
    + getTotalRentsForHousing(): long
    + getTotalRentsForAllHousings(): int
    + getTotalTimesHasRentedCurrentHousing(): long
    + getRentalsForCurrentHousing(): List<Housing>
}

OwnerRentalView -->Owner: owner
OwnerRentalView -->Housing: housing


class OwnerView {
    - owner: Owner
    - currentHousing: Housing

    + OwnerView(owner: Owner, currentHousing: Housing)
    + getAllScores(): List<Integer>
    + getAverageScore(): double
    + getCreatedOn(): LocalDateTime
    + getOwnerRentalView(): OwnerRentalView
}

OwnerView -->Owner: owner
OwnerView -->Housing: currentHousing




interface SearchFilter {
	+filter(housingList: List<Housing> ): List<Housing> 
}

interface Categories{
	+  add(category:Category) : void
}

interface HousingTypes{
	+ add(housingType : HousingType) : void
}

interface Services{
	+  add(service:Service):void
	+  getAll():List<Service>
}

interface BookingAcceptedObserver{
	+ notifyBookingAccepted( booking:Booking) : void
}

interface BookingAcceptanceStrategy{
	+  isAcceptable(booking:Booking):boolean
}

interface BookingCancelledObserver{
	+  notifyBookingCancelled(booking:Booking):void
}

interface BookingStrategy {
	+ process(booking:Booking):void
}

interface EmailSender {
	+  sendEmail(to:String, body:String):void
}

interface EventPublisher {
	+ subscribeToPriceDrop(subscriber:PriceDropSubscriber):void
    + unsubscribeFromPriceDrop(subscriber:PriceDropSubscriber):void
    + subscribeToReservationCancelled(subscriber:ReservationCancelledSubscriber):void
    + unsubscribeFromReservationCancelled(subscriber:ReservationCancelledSubscriber):void
}

interface PaymentMethod{
	+applyCharge(amount:double):void
	+ receivePayment(amount:double):void
}

interface PriceCalculatorInterface {
	+ getPrice(range:DateRange):double
}

interface PriceDropSubscriber {
	+ onPriceDrop(message:String):void
}

interface Rankeable {
	+ addRanking(ranking:Ranking):void
	+ getRankings():List<Ranking>
}

interface Ranker {
	+ rank(ranking:Ranking):void
}

interface ReservationAcceptedSubscriber {
	+ onReservationAccepted(booking:Booking):void
}

interface ReservationCancelledSubscriber {
    + onReservationCancelled(message:String):void
}

interface Service {
	+ getName():String
}




@enduml