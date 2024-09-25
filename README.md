# KNAPSACK 3D GROUP 38

## INTRODUCTION

![Game Screenshot](https://i.ibb.co/d007TZT/Image-21-01-24-at-20-09.jpg)

This project addresses a problem regarding the sorting of packages inside a cargo container. The packages are divided into two categories: cubical parcels and pentominoes. Pentominoes are composed of five equal-sized squares. Users can assign values to each package, determining which should be prioritized to achieve the highest total value when summed up. Both parcels and pentominoes come in three variants. The pentominoes implemented in this application are P, T, and L.

![Pentominoes Image](https://www.cimt.org.uk/resources/puzzles/pentoes/pents1.gif)

## SETTINGS

The container, in which the packages are stored, measures 16.5m x 2.5m x 4.0m.

The dimensions of the parcels are as follows:  
- A: 1.0m x 1.0m x 2.0m, Color: BEIGE  
- B: 1.0m x 1.5m x 2.0m, Color: ROYAL BLUE  
- C: 1.5m x 1.5m x 1.5m, Color: ORANGE  

Each pentomino is constructed from 5 smaller cubes, each measuring 0.5m x 0.5m x 0.5m.

These settings are final and cannot be altered by the user.

## GREEDY ALGORITHM

The bot operates based on a greedy algorithm. This algorithm makes the best decision based on its current position, without necessarily considering the best long-term decisions. While the solution is not always the most optimal, it is relatively fast, and the outcome is generally satisfactory. The algorithm begins by filling the bottom-back-left corner of the container with the highest value package. If there is no space, it tries with the other two options, and so on.

## CREDITS

This project is part of the Bachelor's program in Computer Science at Maastricht University.  
Author: Alexander Stoychev  
Co-Authors: Jonah Heyer, Louis Courtier, Guillaume Lefebvre, Filippo Barbera


