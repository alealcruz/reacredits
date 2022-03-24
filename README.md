# reacredits

Automation tool intended for REA credits creation.
Internal name conventions:
1. REA name like REAXX (example: REA01)
2. Image file name like IMG_MAT6PRI_ (example: IMG_MAT6PRI_EXAMPLE_V01.jpg)
3. Video file name like VID_MAT6PRI_ (example: VID_MAT6PRI_EXAMPLE_V01.jpg)

Full example in CLI:
java -jar creditsBuilder.jar -xml contentv3.xml -output creditsREAXX -img IMG_MAT6PRI_ -vid VID_MAT6PRI_ -rea REA03_

Java version 7.x is required.
