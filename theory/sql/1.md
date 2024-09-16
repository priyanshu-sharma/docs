### SQL ASSIGNMENT

-- 1. What is the total amount each customer spent at the restaurant?

```
Select s.customer_id, sum(m.price) from sales s inner join menu m on s.product_id = m.product_id group by s.customer_id;
```


-- 2. How many days has each customer visited the restaurant?

```
select customer_id, count(distinct(order_date)) from sales group by customer_id;
```

-- 3. What was the first item from the menu purchased by each customer?

```
With output_data as (select * , ROW_NUMBER() OVER (Partition by customer_id order by order_date) from sales) select * from output_data o inner join menu m on o.product_id = m.product_id where row_number = 1;
```


-- 4. What is the most purchased item on the menu and how many times was it purchased by all customers?

```
with output_data as (Select s.product_id, m.product_name, count(*) from sales s
inner join menu m on s.product_id = m.product_id group by s.product_id, m.product_name) sel
ect * from output_data order by count desc limit 1;
```

-- 5. Which item was the most popular for each customer?

```
With output_data as (Select s.customer_id as customer_id, m.product_name, count(*) as total from sales s inner join menu m on s.product_id = m.product_id group by s.customer_id, m.product_name), formatted_data as (select customer_id, product_name, Rank() Over (Partition by customer_id Order by total desc) from output_data) select * from formatted_data where rank = 1;
```

-- 6. Which item was purchased first by the customer after they became 	a member?

```
with output_data as (
	select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date <= s.order_date and m.customer_id = s.customer_id
), formatted_data as ( select *, ROW_NUMBER() OVER (Partition by customer_id order by order_date)  from output_data ) select * from formatted_data fd inner join menu m on fd.product_id = m.product_id where row_number = 1;

```

-- 7. Which item was purchased just before the customer became a member?

```
with output_data as (
	select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date > s.order_date and m.customer_id = s.customer_id
), formatted_data as ( select *, ROW_NUMBER() OVER (Partition by customer_id order by order_date desc)  from output_data ) select * from formatted_data fd inner join menu m on fd.product_id = m.product_id where row_number = 1;
```

-- 8. What is the total items and amount spent for each member before they became a member?

```
with output_data as (
	select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date > s.order_date and m.customer_id = s.customer_id
), formatted_data as (  select * from output_data o inner join menu m on o.product_id = m.product_id )
select customer_id, sum(price) from formatted_data group by customer_id;
```  

-- 9.  If each $1 spent equates to 10 points and sushi has a 2x points multiplier - how many points would each customer have?

```
with output_data as (select * from sales s inner join menu m on s.product_id = m.product_id ),
sd as (select * from output_data where product_name = 'sushi'),
nsd as (select * from output_data where product_name != 'sushi'),
final_output as (select customer_id, 10 * price as point from nsd UNION select customer_id, 2 * price as point from sd) select customer_id, sum(point) from final_output group by customer_id;
```

-- 10. In the first week after a customer joins the program (including their join date) they earn 2x points on all items, not just sushi - how many points do customer A and B have at the end of January?

```
with output_data as (
	select s.customer_id, s.order_date, s.product_id from members m inner join sales s on m.join_date <= s.order_date and m.customer_id = s.customer_id and m.join_date +  7 >= s.order_date
), formatted_data as ( select o.customer_id, 2 * m.price as points from output_data o inner join menu m on o.product_id = m.product_id ) select customer_id, sum(points) from formatted_data group by customer_id;