(ns re-frame-example.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-example.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(defn filter-todos [coeffects [_ status]]
  (let [db (:db coeffects)]
    {:db (assoc db :todo-filtering status)}))

(defn delete-todo [coeffects [_ todo]]
  (let [db (:db coeffects)
        removed (remove #(= (:id %) (:id todo)) (:todos db))]
    {:db (assoc db :todos removed)}))

(defn done-todo [coeffects [_ todo]])

(re-frame/reg-event-fx
  :filter-todo
  filter-todos)

(re-frame/reg-event-fx
  :delete-todo
  delete-todo)

(re-frame/reg-event-fx
  :done-todo
  done-todo)