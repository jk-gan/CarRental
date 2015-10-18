CREATE TABLE Car(
	CarID INT 
	CONSTRAINT Car_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	PlateNo VARCHAR(10) 
	CONSTRAINT PlateNo_NN NOT NULL
	CONSTRAINT PlateNo_UQ UNIQUE,
	Model VARCHAR(100) 
	CONSTRAINT Model_NN NOT NULL,
	Price DOUBLE 
	CONSTRAINT Price_NN NOT NULL,
	Status INT 
	CONSTRAINT Status_NN NOT NULL,
	CONSTRAINT Price_CK CHECK (Price > 0)
);

CREATE TABLE Customer (
	CustomerID INT 
	CONSTRAINT Customer_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	Name VARCHAR(100)
	CONSTRAINT Name_NN NOT NULL,
	IdentityCardNo VARCHAR(20)
	CONSTRAINT IdentityCardno_NN NOT NULL
	CONSTRAINT IdentityCardNo_UQ UNIQUE,
	PhoneNo VARCHAR(20)
	CONSTRAINT Phone_NN NOT NULL
);

CREATE TABLE Rental (
	RentalID INT 
	CONSTRAINT Rental_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	CarID INT 
	CONSTRAINT Rental_CarID_NN NOT NULL
	CONSTRAINT Rental_CarID_FK REFERENCES Car (CarID),
	CustomerID INT 
	CONSTRAINT Rental_CustomerID_NN NOT NULL
	CONSTRAINT Rental_CustomerID_FK REFERENCES Customer (CustomerID),
	RentalStart TIMESTAMP
	CONSTRAINT RentalStart_NN NOT NULL,
	RentalEnd TIMESTAMP
	CONSTRAINT RentalEnd_NN NOT NULL,
	Amount DOUBLE
	CONSTRAINT Amount_NN NOT NULL,
	CONSTRAINT RentalEND_CK CHECK (RentalEnd > RentalStart),
	CONSTRAINT Amount_CK CHECK (Amount > 0)
);