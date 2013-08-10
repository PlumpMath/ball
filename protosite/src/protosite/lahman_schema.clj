(ns protosite.lahman-schema
  (:require [datomic.api :only [db] :as d]))

(def master-types
              {"ahmanID" :db.type/long
               "playerID" :db.type/string
               "managerID" :db.type/string
               "hofID" :db.type/string
               "birth" :db.type/instant
               "birthCountry" :db.type/string
               "birthState" :db.type/string
               "birthCity" :db.type/string
               "death" :db.type/instant
               "deathCountry" :db.type/string
               "deathState" :db.type/string
               "deathCity" :db.type/string
               "nameFirst" :db.type/string
               "nameLast" :db.type/string
               "nameNote" :db.type/string
               "nameGiven" :db.type/string
               "nameNick" :db.type/string
               "weight" :db.type/long
               "height" :db.type/long
               "bats" :db.type/string
               "throws" :db.type/string
               "debut" :db.type/instant
               "finalGame" :db.type/instant
               "college" :db.type/string
               "lahman40ID" :db.type/string
               "lahman45ID" :db.type/string
               "retroID" :db.type/string
               "holtzID" :db.type/string
               "bbrefID" :db.type/string})
(def batting-types
              {"playerID" :db.type/string
               "yearID" :db.type/long
               "stint" :db.type/long
               "teamID" :db.type/string
               "lgID" :db.type/string
               "G" :db.type/long
               "G_batting" :db.type/long
               "AB" :db.type/long
               "R" :db.type/long
               "H" :db.type/long
               "2B" :db.type/long
               "3B" :db.type/long
               "HR" :db.type/long
               "RBI" :db.type/long
               "SB" :db.type/long
               "CS" :db.type/long
               "BB" :db.type/long
               "SO" :db.type/long
               "IBB" :db.type/long
               "HBP" :db.type/long
               "SH" :db.type/long
               "SF" :db.type/long
               "GIDP" :db.type/long
               "G_old" :db.type/long})
(def team-franchise-types 
              {"franchID" :db.type/string
               "franchName" :db.type/string
               "active" :db.type/string
               "NAssoc" :db.type/string})
(def team-types
              {"yearID" :db.type/long
               "lgID" :db.type/string
               "teamID" :db.type/string
               "franchID" :db.type/string
               "divID" :db.type/string
               "Rank" :db.type/long
               "G" :db.type/long
               "Ghome" :db.type/long
               "W" :db.type/long
               "L" :db.type/long
               "DivWin" :db.type/string
               "WCWin" :db.type/string
               "LgWin" :db.type/string
               "WSWin" :db.type/string
               "R" :db.type/long
               "AB" :db.type/long
               "H" :db.type/long
               "2B" :db.type/long
               "3B" :db.type/long
               "HR" :db.type/long
               "BB" :db.type/long
               "SO" :db.type/long
               "SB" :db.type/long
               "CS" :db.type/long
               "HBP" :db.type/long
               "SF" :db.type/long
               "RA" :db.type/long
               "ER" :db.type/long
               "ERA" :db.type/float
               "CG" :db.type/long
               "SHO" :db.type/long
               "SV" :db.type/long
               "IPouts" :db.type/long
               "HA" :db.type/long
               "HRA" :db.type/float
               "BBA" :db.type/long
               "SOA" :db.type/long
               "E" :db.type/long
               "DP" :db.type/long
               "FP" :db.type/float
               "name" :db.type/string
               "park" :db.type/string
               "attendance" :db.type/long
               "BPF" :db.type/long
               "PPF" :db.type/long
               "teamIDBR" :db.type/string
               "teamIDlahman45" :db.type/string
               "teamIDretro" :db.type/string})
(def pitching-types
              {"playerID" :db.type/string
               "yearID" :db.type/long
               "stint" :db.type/long
               "teamID" :db.type/string
               "lgID" :db.type/string
               "W" :db.type/long
               "L" :db.type/long
               "G" :db.type/long
               "GS" :db.type/long
               "CG" :db.type/long
               "SHO" :db.type/long
               "SV" :db.type/long
               "IPouts" :db.type/long
               "H" :db.type/long
               "ER" :db.type/long
               "HR" :db.type/long
               "BB" :db.type/long
               "SO" :db.type/long
               "BAOpp" :db.type/long
               "ERA" :db.type/long
               "IBB" :db.type/long
               "WP" :db.type/long
               "HBP" :db.type/long
               "BK" :db.type/long
               "BFP" :db.type/long
               "GF" :db.type/long
               "R" :db.type/long
               "SH" :db.type/long
               "SF" :db.type/long
               "GIDP" :db.type/long})

(def types (merge master-types batting-types team-types 
                  team-franchise-types pitching-types))

(def index-keys #{"lahmanID" "playerID" "teamID" "yearID" "franchiseID"})

(def schema (for [[ident typ] types]
                   {:db/id (d/tempid :db.part/db)
                    :db/ident (keyword (str "lahman/" ident))
                    :db/valueType typ
                    :db/doc ident
                    :db/cardinality :db.cardinality/one
                    :db/index (contains? index-keys ident)
                    :db.install/_attribute :db.part/db}))
