(ns assistant.bot
  (:require
    [clojure.tools.logging :as log]
    [assistant.bot.event :as event]
    [assistant.bot.util :refer [connect disconnect]]
    [manifold.stream :as mfs]))

(defn run-bot [config]
  (let [{:keys [event-ch msg-ch]
         :as chans} (connect config)]
    @(mfs/consume (fn [[type data]]
                    (try (event/handle msg-ch type data {:config config})
                      (catch Exception e ; need something better
                        (log/error e)))) event-ch)
    (disconnect chans)))
