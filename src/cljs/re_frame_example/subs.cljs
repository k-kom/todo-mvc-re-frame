(ns re-frame-example.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::todos
  (fn [db] (:todos db)))

(re-frame/reg-sub
  ::todo-filtering
  (fn [db] (:todo-filtering db)))

