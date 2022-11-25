# ITEXT7: Java Library for creating pdf files
Wanna see the [source code](https://github.com/itext/itext7/tree/develop)
Wanna know what are all the Border options? 

## Structure
Paragraph Object vs Text Object? \
paragraph act like block element and text act like inline element

sc## Style
you can control margin, padding , rotating , width, word spacing , padding, Height, color, font weight, size etc...
1. create a style object. A container object for style properties of an element
2. call the addStyle(pass the style object) on the element you want to style (Text, Paragraph)

### Font Decoration: Italic, bold, stroke, color - you need to call those methods
italic: setItalic()
bold: .setBold()
underline: setUnderline()
more then one: you can chain methods like .setBold().setItalic()
color: setFontColor()
stroke: setStrokeColor(Color Enume)
stroke only outline: setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.STROKE).setStrokeColor(Color Enum).
setStrokeWidth(int f) => f since it's a float

### Customize Color
use: new DeviceRgb(rgbvalue) instead of: Color.COLORNAME

## Tabels
you create a table object once and that you add cells. cells are added from left to right

1. create a Table object and pass to its constructor the width of each col. as an arg
2. create Cell Object
3. on the Cell object call the add method and add a string
4. call the addCell() on the table object and pass the Cell object as arg

### Add Border to table



https://github.com/itext/itext7/tree/develop