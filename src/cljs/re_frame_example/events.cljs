(ns re-frame-example.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-example.db :as db]
   ))

;;; event handler を登録します (domino 2)
;;; event handler は coeffect と event を受け取って、 app-db を更新します

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

;;; event handler は絶対に pure であるべきなので、 event に反応して別の event を起こしたいときは、
;;; reg-cofx を使って、 あらかじめ定義しておいた(副作用のある)関数を呼び出すためのデータを effect として返します
(re-frame/reg-event-fx
  :filter-todo
  filter-todos)

(re-frame/reg-event-fx
  :delete-todo
  delete-todo)

(re-frame/reg-event-fx
  :done-todo
  done-todo)