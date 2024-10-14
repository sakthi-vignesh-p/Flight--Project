
import java.util.*;

class flight {
    static int id=0;
    private int _flightId = 0;
    private int _flghtSeats = 0;
    private int _ticketPrice = 0;
    ArrayList<booking>passengers;


    void setFlight()
    {
        flight.id=id+1;
        this._flightId=id;
        this._flghtSeats=50;
        this._ticketPrice=5000;
        passengers=new ArrayList<>();
    }

    static void line() {
        System.out.println("_________________________________________________");
    }

    void displayFlightDetails()
    {
        System.out.println("Flight number :"+this._flightId);
        System.out.println("Available tickets :"+this._flghtSeats);
        System.out.println("Ticket price per person :"+this._ticketPrice);
        line();
    }

    void displayPassengerDetails()
    {   line();
        System.out.println("Flight Number :"+this._flightId);
        System.out.println("Passengers-----------------");

        if(this.passengers.size( )> 0)
        {
            for(booking p:this.passengers)
            {   System.out.println("Booking Id : "+p.getPassengerId());
                System.out.println("name :" + p._passengerName);
                System.out.println("Booked Tickets :"+p._bookedTickets);
                line();
            }
        }
        else
        {
            System.out.println("No passengers on this flight");
            line();
        }
        
    }

    int getFlightId()
    {
        return  this._flightId;
    }

    int getFlightSeats()
    {
        return  this._flghtSeats;
    }

    int getTicketPrice()
    {
        return  this._ticketPrice;
    }

    void updateFlightDetails(int tickets,booking pass) {
        this._ticketPrice += tickets * 200;
        this._flghtSeats-=tickets;
        this.passengers.add(pass);
    }

    void cancelTicket(booking b)
 {
     this._ticketPrice-=b.get_bookedTickets()*200;
     this._flghtSeats+= b.get_bookedTickets();
     this.passengers.remove(b);
 }
    }  //flight class

    class booking
    {
        static int id=0;
        int _passengerId=0;
        String _passengerName=null;
        int _bookedTickets=0;
        int _amount=0;

        void setPassengerDetails(String name,int tickets,int price)
        {
            id+=1;
            this._passengerId=id;
            this._passengerName=name;
            this._bookedTickets=tickets;
            this._amount=tickets*price;
        }

  int getPassengerId()
  {
     return this._passengerId;
  }

  int get_bookedTickets()
        {
            return this._bookedTickets;
        }
    }//booking class

    public class Flight {

    static  void  line()
    {
        System.out.println("________________________________________________________");
    }
        public static void main(String[] args) {
            Scanner scan=new Scanner(System.in);

            ArrayList<flight>flights=new ArrayList<>();
           ArrayList<booking>bookings=new ArrayList<>();

           for(int i=1;i<=2;i++)
           {
               flight flight=new flight();
               flight.setFlight();
               flights.add(flight);
           }

            boolean loop=true;
            flight currentFlight=null;
            while(loop)
            {
                int option;
                 line();
                System.out.println("1 for Booking"+"\n"+"2 for cancel"+"\n"+"3 for summery"
                        +"\n"+"4 for Exit");
                line();
                option=scan.nextInt();

                switch(option)
                {
                    case  1:
                        System.out.println("_______________Flight Details__________________");
                        for(flight f:flights)
                        {
                            f.displayFlightDetails();
                        }
                        System.out.println("Enter flight Number");

                        int flightNumber=scan.nextInt();

                        //Check
                        if(flightNumber>flights.size())
                        {
                            System.out.println("Invalid flight id");
                            break;
                        }

                        for(flight f:flights)
                        {
                            if(f.getFlightId()==flightNumber)
                            {
                                currentFlight=f;
                                break;
                            }
                        }
                        //booking work
                        System.out.println("Enter number of tickets");
                        int tickets=scan.nextInt();

                        if(currentFlight.getFlightSeats()<tickets)
                        {
                            System.out.println("Only "+currentFlight.getFlightSeats()+" tickets Available on "+
                                   "flight number "+currentFlight.getFlightId());
                            break;
                        }

                        System.out.println("Enter Name");
                        String name=scan.next();
                        booking passenger=new booking();
                        passenger.setPassengerDetails(name,tickets,currentFlight.getTicketPrice());
                        currentFlight.updateFlightDetails(tickets,passenger);
                        bookings.add(passenger);
                        System.out.println("Booked "+tickets+" tickets Successfully");
                        break;

                    case 2:
                        currentFlight=null;
                        System.out.println("Enter passenger id");
                        int passengerId=scan.nextInt();

                        if(bookings.size()<passengerId)
                        {
                            System.out.println("Invalid passenger Id");
                            break;
                        }
                        System.out.println("Enter flightNumber Id");
                        int flightnum=scan.nextInt();

                       if(flights.size()<flightnum)
                       {
                           System.out.println("Invalid flight id");
                           break;
                       }

                      for(flight f:flights)
                      {
                          if(f.getFlightId()==flightnum  && f.passengers.size()>0)
                          {
                              currentFlight=f;
                              break;
                          }
                      }

                      if(currentFlight==null)
                      {
                          System.out.println("No bookings found on this flight");
                          break;
                      }

                      booking currentpassenger=null;
                      for(booking b:bookings)
                      {
                          if(b.getPassengerId()==passengerId)
                          {
                              currentpassenger=b;
                              break;
                          }
                      }

                      currentFlight.cancelTicket(currentpassenger);
                      bookings.remove(currentpassenger);
                      System.out.println("Canceled "+currentpassenger.get_bookedTickets()+" Tickets");
                        break;

                    case 3:
                          for(flight f:flights)
                          {
                              f.displayPassengerDetails();
                          }
                          break;
                    case  4:
                        loop=false;
                        break;

                    default:
                        System.out.println("Invalid option");
                        break;

                }//switch
            }//loop
        }
    }
