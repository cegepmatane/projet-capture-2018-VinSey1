#!/bin/bash

# Chemin des dossier a sauvegarder
DOSSIERS="/home/ubuntu/capture/"

# Date du jour
MAINTENANT=$(date +"%F")

# Emplacement des sauvegardes du serveur
SAUVEGARDE="/var/sauvegarde/$MAINTENANT"

# Nom de la sauvegarde = note d'hote.temps.tar.gz
FICHIERBASE="capture-nodejs.$(date +'%T').tar.gz"

# Chemin de fichiers binaires
TAR="/bin/tar"
GZIP="/bin/gzip"
LOGGER="/usr/bin/logger"

# Verification que l'emplacement de sauvegarde existe
[ ! -d $SAUVEGARDE ] && mkdir -p ${SAUVEGARDE}

# Message pour dire que la sauvegarde a commencee
$LOGGER "$0: *** La sauvegarde du serveur commence @ $(date) ***"

# Sauvegarde des fichiers serveur de base
$TAR -czvf ${SAUVEGARDE}/${FICHIERBASE} ${DOSSIERS}

# Message de fin de sauvegarde du serveur
$LOGGER "$0: *** Sauvegarde du serveur terminee @ $(date) ***"