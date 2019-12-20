const graphql = require('graphql');
const User = require('../models/user');
const Car = require('../models/car');
const Booking = require('../models/booking');

const {
    GraphQLObjectType, GraphQLString,
    GraphQLID, GraphQLInt, GraphQLSchema,
    GraphQLList, GraphQLNonNull
} = graphql;


const CarType = new GraphQLObjectType({
    name: 'Car',
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
                return Booking.find({ userID: parent.id });
            }
        }
    })
});

const BookingType = new GraphQLObjectType({
    name: 'Booking',

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


const RootQuery = new GraphQLObjectType({
    name: 'RootQueryType',
    fields: {
        user: {
            type: UserType,
            args: { id: { type: GraphQLID } },
            resolve(parent, args) {

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

const Mutation = new GraphQLObjectType({
    name: 'Mutation',
    fields: {
        addCar: {
            type: CarType,
            args: {
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
            },
            resolve(parent, args) {
                let user = new User({
                    email: args.email,
                    password: args.password,
                })
                return user.save()
            }
        },
        addBooking: {
            type: BookingType,
            args: {
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


module.exports = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});