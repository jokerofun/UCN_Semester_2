package Model;


/**
 * Customer with an id(customerNumber), age, contact info from Person superclass and the group he is associated to.
 *
 * @author Alexandru Krausz, Martin Benda, Sebastian Labuda, Simeon Kolev
 * @version 2019.12.3
 */
public class Customer extends Person{
   //instance variables
   private int customerNumber;
   private int age;
   private Group group;
   
   /**
     * Constructor for Customer
     * @param customerNumber of the Customer
     * @param age of the Customer
     * @param name from Person superclass
     * @param email from Person superclass
     * @param address from Person superclass
     * @param phoneNumber from Person superclass
     * @param group associated with Customer
     */
   public Customer(Group group)
   {
       super("Alex","al@gmail.com","NU Str. 10","12454865");
       this.group = group;
   }
   
   /**
    * Returns the name of the Group, the Customer is associated to
    * @return name of the Group
    */
   public String getGroupName()
   {
       return group.getNameGroup();
   }

   /**
    * Returns the discount of the Group, the Customer is associated to
    * @return discount of the Group
    */
   public int getGroupDiscount()
   {
       return group.getDiscountGroup();
   }
   
   /**
     * Set the name of this Customer
     * @param name
     */
   public void setName(String name)
   {
       setNamePerson(name);
   }

   /**
     * Set the email of this Customer
     * @param email
     */
   public void setEmail(String email)
   {
       setEmailPerson(email);
   }

   /**
     * Set the address of this Customer
     * @param address
     */
   public void setAddress(String address)
   {
       setAddressPerson(address);
   }

   /**
     * Set the phoneNumber of this Customer
     * @param phoneNumber
     */
   public void setPhoneNumber(String phoneNumber)
   {
       setPhoneNumberPerson(phoneNumber);
   }

   /**
     * Set the customerNumber of this Customer
     * @param customerNumber
     */
   public void setCustomerNumber(int customerNumber)
   {
       this.customerNumber = customerNumber;
   }

   /**
     * Set the age of this Customer
     * @param age
     */
   public void setAge(int age)
   {
       this.age = age;
   }

   /**
     * @return name of this Customer
     */
   public String getName()
   {
       return getNamePerson();
   }
   
   /**
     * @return email of this Customer
     */
   public String getEmail()
   {
       return getEmailPerson();
   }
   
   /**
     * @return address of this Customer
     */
   public String getAddress()
   {
       return getAddressPerson();
   }
   
   /**
     * @return phoneNumber of this Customer
     */
   public String getPhoneNumber()
   {
       return getPhoneNumberPerson();
   }
   
   /**
     * @return customerNumber of this Customer
     */
   public int getCustomerNumberCustomer()
   {
       return customerNumber;
   }
   
   /**
     * @return age of this Customer
     */
   public int getAgeCustomer()
   {
       return age;
   }
}
