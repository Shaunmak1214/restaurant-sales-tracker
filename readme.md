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
1. [x] Singleton for Utils 
2. [x] Builder for User
3. [x] Builder for Discount
4. [x] Factory for Drinks
5. [x] Factory for Food
6. [x] Abstract class for Items (to hold: Drinks and Foods)
7. [x] Builder for Sales
8. [] Logic to read db and assign objects using builder (Foods, Drinks Item List) (Using Utils as Singleton)
9. [] Logic for calculating discounts
10. [] Full flow of ordering 
11. [] Full flow of ordering with discounts
12. [] UI/UX Design and Implementation