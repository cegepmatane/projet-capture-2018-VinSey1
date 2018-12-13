const CapteurDAO = require('./accesseur/CapteurDAO');

console.log('L\'app est lancee');

// temps UTC
const maintenant = new Date().toISOString().
replace(/T/, ' ').
replace(/\..+/, '');

// Random entre 1 et 100
const valeur = Math.floor((Math.random() * 100) + 1);

const capteurDAO = new CapteurDAO();
capteurDAO.ajouter({
    date: maintenant,
    latitude: 48.840689,
    longitude: -67.497454,
    valeur: valeur
}).catch(console.error);