const CapteurDAO = function () {
    const Datastore = require('@google-cloud/datastore');

    this.ajouter = async donnees => {
        console.log('CapteurDAO.ajouter()');

        // projectId du dossier .json venant de https://console.cloud.google.com/apis/credentials/serviceaccountkey
        const projectId = 'PAS-DE-TES-AFFAIRES';

        // Creer le client pour parler a la bd
        const datastore = new Datastore({
            projectId: projectId,
        });

        // Genre sur Datastore un peu comme une table
        const genre = 'capteur';

        // Necessaire pour identifier ou va la query
        const clefTache = datastore.key([genre]);

        // Prepare l'entite
        const tache = {
            key: clefTache,
            data: donnees,
        };

        // Ajoute la tache ou donnees dans une entite
        await datastore.save(tache);
        console.log('Ajouter ' + JSON.stringify(tache.data));
    }
}
module.exports = CapteurDAO;