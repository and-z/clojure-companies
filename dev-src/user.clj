(ns user
  (:require [aleph.http :as http]
            [byte-streams :as bs]
            [hickory.core :as h]
            [hickory.select :as sel]))

(def cc-url "https://clojure.org/community/companies")

(defn url->hickory [url]
  (-> @(http/get url)
      :body
      bs/to-string
      h/parse
      h/as-hickory))

(defn table-rows [hdoc]
  (-> (sel/descendant (sel/tag :tbody)
                      (sel/tag :tr))
      (sel/select hdoc)))

(defn name-url [hrow]
  (-> (sel/descendant (sel/tag :td)
                      (sel/tag :p))
      (sel/select hrow)))

(def content-str (comp clojure.string/trim first :content))

(defn content [htuple]
  (mapv content-str htuple))

(comment


  (->> (url->hickory cc-url)
       (table-rows)
       (map name-url)
       (map content)
       (count)
       #_(run! println))

  ;; keep newline
  )
