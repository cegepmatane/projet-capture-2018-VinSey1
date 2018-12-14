#!/bin/bash
# Le script sert a sauvegarder les fichiers importants du serveur

# Date du jour
MAINTENANT=$(date +"%F")

# Emplacement des sauvegardes du serveur
SAUVEGARDE="/var/sauvegarde/$MAINTENANT"

# Nom de la sauvegarde = note d'hote.temps.tar.gz
FICHIERPSQL="$(hostname).$(date +'%T').psql.sql.gz"

# Informations connexion PostgreSQL
UTILISATEURPSQL="postgres"
MDPPSQL="password"
NOMBD="capture"

# Chemin de fichiers binaires
DUMPPSQL="/usr/bin/pg_dump"
GZIP="/bin/gzip"
LOGGER="/usr/bin/logger"

# Verification que l'emplacement de sauvegarde existe
[ ! -d $SAUVEGARDE ] && mkdir -p ${SAUVEGARDE}

# Message pour dire que la sauvegarde a commencee
$LOGGER "$0: *** La sauvegarde du serveur commence @ $(date) ***"

# Sauvegarde PostgreSQL
$DUMPPSQL --username=${UTILISATEURPSQL} --inserts -C ${NOMBD} | $GZIP -9 > ${SAUVEGARDE}/${FICHIERPSQL}

# Message de fin de sauvegarde du serveur
$LOGGER "$0: *** Sauvegarde du serveur terminee @ $(date) ***"