package com.orientechnologies.orient.core.db.record;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ORecordLazySetTest {

  private ODatabaseDocumentTx db;
  private ODocument           doc1;
  private ODocument           doc2;
  private ODocument           doc3;
  private ORID                rid1;
  private ORID                rid2;
  private ORID                rid3;

  @BeforeClass
  public void before() {
    db = new ODatabaseDocumentTx("memory:tesLocal");
    db.create();
    doc1 = db.save(new ODocument().field("doc1", "doc1"));
    rid1 = doc1.getIdentity();
    doc2 = db.save(new ODocument().field("doc2", "doc2"));
    rid2 = doc2.getIdentity();
    doc3 = db.save(new ODocument().field("doc3", "doc3"));
    rid3 = doc3.getIdentity();
  }

  @AfterClass
  public void after() {
    db.drop();
  }

  @Test
  public void testConvertToRecord() {
    ORecordLazySet set = new ORecordLazySet(new ODocument());
    set.add(rid1);
    set.add(rid2);
    set.add(rid3);
    set.convertLinks2Records();
    assertEquals(set.size(), 3);
    for (OIdentifiable oIdentifiable : set) {
      assertTrue(oIdentifiable instanceof ODocument);
    }
  }

  @Test
  public void testIteratorConvertToRecord() {
    ORecordLazySet set = new ORecordLazySet(new ODocument());
    set.add(rid1);
    set.add(rid2);
    set.add(rid3);
    for (OIdentifiable oIdentifiable : set) {
      assertTrue(oIdentifiable instanceof ODocument);
    }
    assertEquals(set.size(), 3);
  }

  @Test
  public void testConvertToLink() {
    ORecordLazySet set = new ORecordLazySet(new ODocument());
    set.add(rid1);
    set.add(rid2);
    set.add(rid3);
    for (OIdentifiable oIdentifiable : set) {
      assertTrue(oIdentifiable instanceof ODocument);
    }
    set.convertRecords2Links();
    assertEquals(set.size(), 3);
    Iterator<OIdentifiable> val = set.rawIterator();
    while (val.hasNext()) {
      assertTrue(val.next() instanceof ORecordId);
    }
    assertEquals(set.size(), 3);
  }

  @Test(enabled = false)
  public void testDocumentConvertToLink() {
    ORecordLazySet set = new ORecordLazySet(new ODocument());
    set.add(doc1);
    set.add(doc2);
    set.add(doc3);
    for (OIdentifiable oIdentifiable : set) {
      assertTrue(oIdentifiable instanceof ODocument);
    }
    set.convertRecords2Links();
    assertEquals(set.size(), 3);
    Iterator<OIdentifiable> val = set.rawIterator();
    while (val.hasNext()) {
      assertTrue(val.next() instanceof ORecordId);
    }
    assertEquals(set.size(), 3);
  }

}