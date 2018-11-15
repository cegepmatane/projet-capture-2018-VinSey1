#!/usr/bin/env python
import PCF8591 as ADC
import RPi.GPIO as GPIO
import time
import math

GPIO17 = 17
GPIO.setmode(GPIO.BCM)
status = 1

def configurer():
	ADC.configurer(0x48)
	GPIO.setup	(GPIO17, GPIO.IN)
		
def ecrire(x):
	if x == 1:
		print ('')
		print ('   **************************************************')
		print ('   * Concentration de gas dans l\'air sécuritaire ~ *')
		print ('   **************************************************')
		print ('')
	if x == 0:
		print ('')
		print ('   ************************************************')
		print ('   * Concentration dangereuse de gas dans l\'air! *')
		print ('   ************************************************')
		print ('')

def recupererGas():
	# TODO : Traiter la valeur pour extraire le CO2
	valeur = ADC.lire(0)
	#print(ADC.lire(0))		
	
	global status
	valeurTemporaire = GPIO.input(GPIO17);
	if valeurTemporaire != status:
		ecrire(valeurTemporaire)
		status = valeurTemporaire

	#time.sleep(0.2)

	return valeur

def detruire():
	GPIO.cleanup()

if __name__ == '__main__':
	try:
		configurer()
	except KeyboardInterrupt: 
		detruire()
