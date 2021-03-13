# Dummy Text Generator Plugin

Source code of the intellij IDEA "Dummy Text Generator" plugin: http://plugins.jetbrains.com/plugin?pr=&pluginId=7216
This is a plugin for the various intellij IDEs, working in IntelliJ IDEA Ultimate and Community Edition, 
RubyMine, WebStorm, PhpStorm, PyCharm, PyCharm CE, AppCode, Android Studio and 0xDBE.


## Table of Contents

* [What does it do?](#what-does-it-do)
* [Third Party Resources](#third-party-resources)
* [Changelog](#changelog)
* [Author and License](#author-and-license)


## What does it do?

The plugin adds a random text generator, capable of creating witty texts in different genres.
Created text can be inserted newly at the caret, or replace a selection.
The dummy text generator is added to the main menu, tools menu and into the generate... popup menu (Alt+Insert)

Used from the editor main menu, text is generated in the previously selected genre.
Used from the Tools menu or Generate popup, the plugin allows to choose from several genres:

* Culinary Inspirations
* Esoteric Wisdom
* Fake Latin (similar to "Lorem Ipsum" blindtext)
* Pirate Lingo
* Science Fiction

When replacing a multi-lined selection of text, the generated dummy text maintains the amount of lines.
When replacing a selection of text within a single line, the amount of words is roughly being maintained.
When the replaced text selection consists fully of lower-cased or capital letters or begins with a capital letter,
that previous casing is maintained. Furthermore, the presence or absence of a trailing punctuation mark of a
replaced text selection is being maintained.


## Third Party Resources

The icons used in this plugin have been altered in colors, they originate from the following iconsets:

Freecns Cumulus by Yannick Lung - bubble, library
Icomoon - http://keyamoon.com/ - rocket
iconsimple (freebies) by pixan - yingyang
Miu by LinhPham.me - Pizza
WPZOOM - http://www.wpzoom.com - skull


## Changelog

See https://github.com/kstenschke/dummytext-plugin/blob/master/CHANGELOG.md


## Author and License

Copyright Kay Stenschke

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

"http://www.apache.org/licenses/LICENSE-2.0":http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
