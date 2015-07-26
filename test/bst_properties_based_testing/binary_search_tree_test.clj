(ns bst-properties-based-testing.binary-search-tree-test
  (:use midje.sweet)
  (:require [bst-properties-based-testing.binary-search-tree :as binary-search-tree]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def in-order-traversal-of-bst-sorts-elements-property
  (prop/for-all [v (gen/vector gen/int)]
                (= (binary-search-tree/in-order
                     (binary-search-tree/from-list v))
                   (sort v))))

(defspec in-order-traversal-of-bst-sorts-elements
         100
         in-order-traversal-of-bst-sorts-elements-property)

(facts
  "about binary search tree in-order traversal"

  (fact
    "sorts the elements of a tree without any sons"
    (binary-search-tree/in-order
      (binary-search-tree/from-list [3])) => [3])

  (fact
    "sorts the elements of a tree with left and right sons"
    (binary-search-tree/in-order
      (binary-search-tree/from-list [2 5 1])) => [1 2 5])

  (fact
    "sorts the elements of a complex tree created inserting values one by one"
    (let [tree (->> (binary-search-tree/singleton 4)
                    (binary-search-tree/insert 6)
                    (binary-search-tree/insert 5)
                    (binary-search-tree/insert 8)
                    (binary-search-tree/insert 2)
                    (binary-search-tree/insert 3)
                    (binary-search-tree/insert 1))]
      (binary-search-tree/in-order tree) => [1 2 3 4 5 6 8]))

  (fact
    "sorts the elements of a complex tree created from a list"
    (binary-search-tree/in-order
      (binary-search-tree/from-list [4 6 5 8 2 3 1])) => [1 2 3 4 5 6 8])

  (fact
    "sorts the elements of an empty tree created from an empty list"
    (binary-search-tree/in-order
      (binary-search-tree/from-list [])) => []))