const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const bookingSchema = new Schema({
    carID: String,
    userID: String
});

module.exports = mongoose.model('Booking', bookingSchema);  