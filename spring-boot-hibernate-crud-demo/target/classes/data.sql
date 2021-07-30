insert into
	TBL_CUSTOMER (first_name, last_name,adress,phone,email)
values
  	('Lokesh', 'Gupta','address1','05550253636', 'test@gmail.com'),
  	('John', 'Doe','address2','05000253637', 'xyz@email.com');

insert into
	TBL_BOOK (bookName, bookVendor, bookDescription,quantityInStock,buyPrice)
values
  	('book1', 'vendor1','book description 1',10,100),
  	('book2', 'vendor2','book description 2 ',10,200);

insert into
	TBL_ORDER (orderDate, shippedDate, status,comments,customerNoFK,quantityInOrdered)
values
  	('2021-07-27', '2021-07-27',TRUE,'some comments',1,1),
  	('2021-07-27', '2021-07-27',FALSE,'some comments',2,1);


insert into
	TBL_ORDER_BOOK (bookIdFK ,orderIdFK)
values
  	(1,1);





