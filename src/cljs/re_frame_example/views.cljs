(ns re-frame-example.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-example.subs :as subs]
   ))

(defn greetings []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Bye from " @name]]))

(defn todo-input []
  [:input {:type "text"
           :placeholder "What needs to be done?"}])

(defn todo-component [todo]
  [:div
   [:span [:input {:type "radio"
                   :on-click #(re-frame.core/dispatch [:done-todo todo])}]]
   [:span (:title todo)]
   [:span [:input {:type "button"
                   :value "X"
                   :on-click #(re-frame.core/dispatch [:delete-todo todo])}]]])

(defn todo-list-component []
  (let [todos (re-frame/subscribe [::subs/todos])]
    [:div
     [:ul
      (for [todo @todos]
        ^{:key todo} [:li [todo-component todo]])]]))

(defn todo-stats-component []
  (let [todos (re-frame/subscribe [::subs/todos])]
    [:div (count @todos) " item left"]))

(defn todo-filter-component []
  [:div
   [:input {:type  "button"
            :value "All"
            :on-click #(re-frame.core/dispatch [:filter-todo :all])}]
   [:input {:type  "button"
            :value "Active"
            :on-click #(re-frame.core/dispatch [:filter-todo :active])}]
   [:input {:type  "button"
            :value "Completed"
            :on-click #(re-frame.core/dispatch [:filter-todo :completed])}]])

(defn footer-component []
  [:div
   [todo-stats-component]])

(defn main-panel []
  [:div
   [todo-input]
   [todo-list-component]
   [footer-component]])

