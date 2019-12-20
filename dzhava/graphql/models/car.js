const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const carSchema = new Schema({
    model: String,
    brand: String,
    price: Number
});

module.exports = mongoose.model('Car', carSchema);