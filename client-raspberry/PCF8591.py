#!/usr/bin/env python
#------------------------------------------------------
#
#		This is a program for PCF8591 Module.
#
#		Warnng! The Analog input MUST NOT be over 3.3V!
#    
#		In this script, we use a poteniometer for analog
#   input, and a LED on AO for analog output.
#
#		you can import this script to another by:
#	import PCF8591 as ADC
#	
#	ADC.Setup(Address)  # Check it by sudo i2cdetect -y -1
#	ADC.lire(channal)	# Channal range from 0 to 3
#	ADC.ecrire(Value)	# Value range from 0 to 255		
#
#------------------------------------------------------
import smbus
import time

bus = smbus.SMBus(1)

#pour verifier l'adresse du PCF8591 tapez 'sudo i2cdetect -y -1' dans le terminal.
def configurer(adresseCapteur):
	global adresse
	adresse = adresseCapteur

def lire(canal):
	try:
		if canal == 0:
			bus.write_byte(adresse,0x40)
		if canal == 1:
			bus.write_byte(adresse,0x41)
		if canal == 2:
			bus.write_byte(adresse,0x42)
		if canal == 3:
			bus.write_byte(adresse,0x43)
		bus.read_byte(adresse) # dummy lire pour commencer la conversion
	except Exception as e:
		print ("Adresse: %s" % adresse)
		print (e)
	return bus.read_byte(adresse)

def ecrire(valeur):
	try:
		valeurTemporaire = valeur
		valeurTemporaire = int(valeurTemporaire) # convertit la string en integer
		
		bus.write_byte_data(adresse, 0x40, valeurTemporaire)
	except Exception as e:
		print ("Erreur: Adresse peripherique: 0x%2X" % adresse)
		print (e)

if __name__ == "__main__":
	configurer(0x48)
	while True:
		print ('AIN0 = ', lire(0))
		valeurTemporaire = lire(0)
		valeurTemporaire = valeurTemporaire*(255-125)/255+125 # La DEL ne s'allume pas en bas 125, alors convertire '0-255' to '125-255'
		ecrire(valeurTemporaire)
#		time.sleep(0.3)
