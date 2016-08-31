(ns redgenes.sections.objects.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [redgenes.sections.objects.components.summary :as summary]
            [redgenes.components.table :as table]
            [redgenes.components.collection :as collection]
            [redgenes.components.lighttable :as lighttable]))

(defn main []
  (let [params           (subscribe [:panel-params])
        report           (subscribe [:report])
        categories       (subscribe [:template-chooser-categories])
        templates        (subscribe [:runnable-templates])
        collections      (subscribe [:collections])
        fetching-report? (subscribe [:fetching-report?])]
    (fn []
      [:div.container-fluid
       (if @fetching-report?
         [:i.fa.fa-cog.fa-spin.fa-3x.fa-fw]
         [:div
          [:ol.breadcrumb
           [:li [:a "Home"]]
           [:li [:a "Search Results"]]
           [:li.active [:a "Report"]]]
          [summary/main (:summary @report)]
          (into [:div.collections] (map (fn [query] [lighttable/main query]) @collections))
          (into [:div.templates] (map (fn [[id details]] [table/main details]) @templates))])])))