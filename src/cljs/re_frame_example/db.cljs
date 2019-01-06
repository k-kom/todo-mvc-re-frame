(ns re-frame-example.db)

(def default-db
  {:name           "re-frame"
   :todo-filtering :all
   :todos          [{:title  "cooking"
                     :status :active
                     :id 0}
                    {:title  "clean the room"
                     :status :completed
                     :id 1}]})
