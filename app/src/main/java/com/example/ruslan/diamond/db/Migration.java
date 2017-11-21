package com.example.ruslan.diamond.db;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema realmSchema = realm.getSchema();

        if (oldVersion==0){
            realmSchema.create("Price")
                    .addField("carat",String.class)
                    .addField("amount",Integer.class)
                    .addField("clarity",String.class)
                    .addField("weight",Integer.class)
                    .addField("price",Double.class);
            oldVersion++;
        }
    }
}
