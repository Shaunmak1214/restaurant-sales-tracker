### Requirements
1. GUI (Restraunt)
2. Keep track of sales (food & drinks)
3. Customer place order
3. Issue bill listing the ordered items
4. Promotions runs with discounts, customer have to present discount code. (0-100%)
5. Discount is applicable to the total purchase
6. Both prices with discount and wihout have to be displayed
7. Discount module needs to be modular and extensible in the sense that it can be applied to a single item OR a bundle of items.


### Todos
[x] Utils
[x] Builder for User
[x] Builder for Discount
[x] Factory for Drinks
[x] Factory for Food
[x] Abstract class for Items (to hold: Drinks and Foods)
[x] Builder for Sales
[] Logic to read db and assign objects using builder (Foods, Drinks Item List) (Using Utils as Singleton)
[] Logic for calculating discounts
[] Full flow of ordering 
[] Full flow of ordering with discounts
[] UI/UX Design and Implementation