# SofiaServerNoKill
This xposed module skips the SofiaServer "kill all apps" when going to sleep.
This should replace the old nokill mod from Gustden.

Note: Some apps prevent the unit from going into deep-sleep. The old SofiaServer simply killed these apps. This module doesn't do that. The MCU detects the apps keeping the CPU cores at higher frequency and can completely switch off the unit, resulting in a cold-boot upon switching on the contact. That is not the fault of this module. It is the fault of these bad behaving apps.

