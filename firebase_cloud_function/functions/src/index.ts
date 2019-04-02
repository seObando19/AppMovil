//import * as functions from 'firebase-functions';

const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();
exports.addMessage = functions.https.onRequest((req:any, res:any) => {
    
    return admin.firestore().collection("producto2").add({
        nombre: req.query.nombre,
        tipo: req.query.tipo,
        valor: req.query.valor        
    }).then((r:any)=>{
        console.log("Todo correcto");
        res.send(r)
    }).catch((err:any)=>{
        console.log("Error");
        res.send(err);
    });

  });
