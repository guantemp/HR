/*
 * Copyright (c) 2020 www.hoprxi.com All rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hr.hoprxi.infrastructure.persistence;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.entity.KeyType;
import com.arangodb.entity.UserEntity;
import com.arangodb.model.CollectionCreateOptions;
import com.arangodb.model.HashIndexOptions;
import com.arangodb.model.UserCreateOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK8.0
 * @version 0.0.1 2020-12-05
 */
public final class HRSetup {
    private static final Logger logger = LoggerFactory.getLogger(HRSetup.class);

    public static void setup(String databaseName) {
        ArangoDB arangoDB = ArangoDBUtil.getResource();
        if (arangoDB.db(databaseName).exists()) {
            arangoDB.db(databaseName).drop();
            logger.info("{} be discarded", databaseName);
        }
        arangoDB.createDatabase(databaseName);
        ArangoDatabase db = arangoDB.db(databaseName);
        //vertex
        CollectionCreateOptions vertexOptions = new CollectionCreateOptions();
        vertexOptions.keyOptions(true, KeyType.traditional, 1, 1);
        for (String s : new String[]{"organization", "employee", "department"}) {
            db.createCollection(s, vertexOptions);
        }
        //index
        Collection<String> index = new ArrayList<>();
        //SkiplistIndexOptions indexOptions = new SkiplistIndexOptions().sparse(true);
        index.add("name.name");
        index.add("name.mnemonic");
        HashIndexOptions hashIndexOptions = new HashIndexOptions().unique(false);
        db.collection("organization").ensureHashIndex(index, hashIndexOptions);
        //
        index.clear();
        index.add("name");
        db.collection("group").ensureHashIndex(index, hashIndexOptions);
        db.collection("role").ensureHashIndex(index, hashIndexOptions);
        //edge
        /*
        CollectionCreateOptions edgeOptions = new CollectionCreateOptions().type(CollectionType.EDGES);
        for (String s : new String[]{"subordinate", "act", "create"}) {
            db.createCollection(s, edgeOptions);
        }
        edgeOptions.keyOptions(true, KeyType.traditional, 1, 1);
        db.createCollection("processor", edgeOptions);
        */
        //graph
        Collection<EdgeDefinition> list = new ArrayList<>();
        list.add(new EdgeDefinition().collection("subordinate").from("organization").to("organization", "department"));
        list.add(new EdgeDefinition().collection("processor").from("role").to("resource"));
        db.createGraph("hr", list);
        arangoDB.shutdown();
        logger.info("{} be created", databaseName);
    }

    public static void builderUserAndGrant(String username, String password) {
        ArangoDB arangoDB = ArangoDBUtil.getResource();
        UserCreateOptions userCreateOptions = new UserCreateOptions();
        UserEntity userEntity = arangoDB.getUser(username);
        if (userEntity != null)
            arangoDB.deleteUser(username);
        arangoDB.createUser(username, username);
    }
}
