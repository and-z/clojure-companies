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

(defn select-entries [hdoc]
  (-> (sel/descendant (sel/class :ulist)
                      (sel/tag :ul)
                      (sel/tag :li))
      (sel/select hdoc)))

(defn entry [h-entry]
  (-> (sel/descendant (sel/tag :p)
                      (sel/tag :a))
      (sel/select h-entry)))

(def content-str (comp clojure.string/trim first :content))
(def a-href (comp :href :attrs))

(comment

  (def sample-anchor
    {:type :element, :attrs {:href "http://8thlight.com/", :rel "nofollow"}, :tag :a, :content ["8th Light"]})

  (a-href sample-anchor)

  (->> (url->hickory cc-url)
       (select-entries)
       #_(take 1)
       (mapcat entry)
       (map (juxt content-str a-href))
       (map (fn [[n url]] (hash-map :company-name n, :company-url url)))
       #_(count)
       (run! println))

  ;; keep newline
  )
