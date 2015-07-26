(ns bst-properties-based-testing.binary-search-tree)

(defn singleton [val]
  {:value val})

(def value :value)
(def left :left)
(def right :right)

(defn insert [val tree]
  (let [add-node
        (fn [location node]
          (assoc tree location
                      (if node (insert val node)
                               (singleton val))))]
    (if (<= val (value tree))
      (add-node :left (left tree))
      (add-node :right (right tree)))))

(defn from-list [[root & rest-nodes :as nodes]]
  (if (empty? nodes)
    nil
    (reduce #(insert %2 %1) (singleton root) rest-nodes)))

(defn in-order [tree]
  (if tree
    (concat (in-order (left tree))
            [(value tree)]
            (in-order (right tree)))
    []))

