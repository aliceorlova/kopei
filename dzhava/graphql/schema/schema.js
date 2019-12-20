const graphql = require('graphql');
const User = require('../models/user');
const Car = require('../models/car');
const Booking = require('../models/booking');

const {
    GraphQLObjectType, GraphQLString,
    GraphQLID, GraphQLInt, GraphQLSchema,
    GraphQLList, GraphQLNonNull
} = graphql;

//Schema defines data on the Graph like object types(book type), relation between 
//these object types and describes how it can reach into the graph to interact with 
//the data to retrieve or mutate the data   

const CarType = new GraphQLObjectType({
    name: 'Car',
    //We are wrapping fields in the function as we dont want to execute this ultil 
    //everything is inilized. For example below code will throw error AuthorType not 
    //found if not wrapped in a function
    fields: () => ({
        id: { type: GraphQLID },
        model: { type: GraphQLString },
        brand: { type: GraphQLString },
        price: { type: GraphQLInt }
    })
});

const UserType = new GraphQLObjectType({
    name: 'User',
    fields: () => ({
        id: { type: GraphQLID },
        email: { type: GraphQLString },
        password: { type: GraphQLString },
        booking: {
            type: new GraphQLList(BookingType),
            resolve(parent, args) {
                return Booking.find({ userId: parent.id });
            }
        }
    })
});

const BookingType = new GraphQLObjectType({
    name: 'Booking',
    //We are wrapping fields in the function as we dont want to execute this ultil 
    //everything is inilized. For example below code will throw error AuthorType not 
    //found if not wrapped in a function
    fields: () => ({
        id: { type: GraphQLID },
        user: {
            type: UserType,
            resolve(parent, args) {
                return User.findById(parent.userID);
            }
        },
        car: {
            type: CarType,
            resolve(parent, args) {
                return Car.findById(parent.carID);
            }
        }
    })
});

//RootQuery describe how users can use the graph and grab data.
//E.g Root query to get all authors, get all books, get a particular 
//book or get a particular author.
const RootQuery = new GraphQLObjectType({
    name: 'RootQueryType',
    fields: {
        user: {
            type: UserType,
            //argument passed by the user while making the query
            args: { id: { type: GraphQLID } },
            resolve(parent, args) {
                //Here we define how to get data from database source

                //this will return the book with id passed in argument 
                //by the user
                return User.findById(args.id);
            }
        },
        users: {
            type: new GraphQLList(UserType),
            resolve(parent, args) {
                return User.find({});
            }
        },
        car: {
            type: CarType,
            args: { id: { type: GraphQLID } },
            resolve(parent, args) {
                return Car.findById(args.id);
            }
        },
        cars: {
            type: new GraphQLList(CarType),
            resolve(parent, args) {
                return Car.find({});
            }
        },
        booking: {
            type: BookingType,
            args: { id: { type: GraphQLID } },
            resolve(parent, args) {
                return Booking.findById(args.id);
            }
        },
        bookings: {
            type: new GraphQLList(BookingType),
            resolve(parent, args) {
                return Booking.find({});
            }
        }
    }
});

//Very similar to RootQuery helps user to add/update to the database.
const Mutation = new GraphQLObjectType({
    name: 'Mutation',
    fields: {
        addCar: {
            type: CarType,
            args: {
                //GraphQLNonNull make these field required
                model: { type: new GraphQLNonNull(GraphQLString) },
                brand: { type: new GraphQLNonNull(GraphQLString) },
                price: { type: new GraphQLNonNull(GraphQLInt) }
            },
            resolve(parent, args) {
                let car = new Car({
                    model: args.model,
                    brand: args.brand,
                    price: args.price
                });
                return car.save();
            }
        },
        addUser: {
            type: UserType,
            args: {
                email: { type: new GraphQLNonNull(GraphQLString) },
                password: { type: new GraphQLNonNull(GraphQLString) },
                //  authorID: { type: new GraphQLNonNull(GraphQLID) }
            },
            resolve(parent, args) {
                let user = new User({
                    email: args.email,
                    password: args.password,
                    //  authorID: args.authorID
                })
                return user.save()
            }
        },
        addBooking: {
            type: BookingType,
            args: {
                //GraphQLNonNull make these field required
                userID: { type: new GraphQLNonNull(GraphQLID) },
                carID: { type: new GraphQLNonNull(GraphQLID) }
            },
            resolve(parent, args) {
                let booking = new Booking({
                    userID: args.userID,
                    carID: args.carID
                });
                return booking.save();
            }
        }
    }
});

//Creating a new GraphQL Schema, with options query which defines query 
//we will allow users to use when they are making request.
module.exports = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});