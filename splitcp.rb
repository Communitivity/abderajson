#!/usr/bin/ruby -w

text = ""
File.open("input.txt", "r").each_line{ |line|  text << line }
puts text.split(" ").map! { |line| line.sub("20090105.215721-1","SNAPSHOT") }.join("\n")
