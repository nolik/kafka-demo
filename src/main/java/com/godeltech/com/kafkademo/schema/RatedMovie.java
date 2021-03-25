/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.godeltech.com.kafkademo.schema;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class RatedMovie extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 919701354587915648L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RatedMovie\",\"namespace\":\"com.godeltech.com.kafkademo.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"title\",\"type\":\"string\"},{\"name\":\"release_year\",\"type\":\"int\"},{\"name\":\"rating\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<RatedMovie> ENCODER =
      new BinaryMessageEncoder<RatedMovie>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<RatedMovie> DECODER =
      new BinaryMessageDecoder<RatedMovie>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<RatedMovie> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<RatedMovie> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<RatedMovie>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this RatedMovie to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a RatedMovie from a ByteBuffer. */
  public static RatedMovie fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long id;
  @Deprecated public java.lang.CharSequence title;
  @Deprecated public int release_year;
  @Deprecated public double rating;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public RatedMovie() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param title The new value for title
   * @param release_year The new value for release_year
   * @param rating The new value for rating
   */
  public RatedMovie(java.lang.Long id, java.lang.CharSequence title, java.lang.Integer release_year, java.lang.Double rating) {
    this.id = id;
    this.title = title;
    this.release_year = release_year;
    this.rating = rating;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return title;
    case 2: return release_year;
    case 3: return rating;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: title = (java.lang.CharSequence)value$; break;
    case 2: release_year = (java.lang.Integer)value$; break;
    case 3: rating = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'title' field.
   * @return The value of the 'title' field.
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'release_year' field.
   * @return The value of the 'release_year' field.
   */
  public java.lang.Integer getReleaseYear() {
    return release_year;
  }

  /**
   * Sets the value of the 'release_year' field.
   * @param value the value to set.
   */
  public void setReleaseYear(java.lang.Integer value) {
    this.release_year = value;
  }

  /**
   * Gets the value of the 'rating' field.
   * @return The value of the 'rating' field.
   */
  public java.lang.Double getRating() {
    return rating;
  }

  /**
   * Sets the value of the 'rating' field.
   * @param value the value to set.
   */
  public void setRating(java.lang.Double value) {
    this.rating = value;
  }

  /**
   * Creates a new RatedMovie RecordBuilder.
   * @return A new RatedMovie RecordBuilder
   */
  public static com.godeltech.com.kafkademo.schema.RatedMovie.Builder newBuilder() {
    return new com.godeltech.com.kafkademo.schema.RatedMovie.Builder();
  }

  /**
   * Creates a new RatedMovie RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new RatedMovie RecordBuilder
   */
  public static com.godeltech.com.kafkademo.schema.RatedMovie.Builder newBuilder(com.godeltech.com.kafkademo.schema.RatedMovie.Builder other) {
    return new com.godeltech.com.kafkademo.schema.RatedMovie.Builder(other);
  }

  /**
   * Creates a new RatedMovie RecordBuilder by copying an existing RatedMovie instance.
   * @param other The existing instance to copy.
   * @return A new RatedMovie RecordBuilder
   */
  public static com.godeltech.com.kafkademo.schema.RatedMovie.Builder newBuilder(com.godeltech.com.kafkademo.schema.RatedMovie other) {
    return new com.godeltech.com.kafkademo.schema.RatedMovie.Builder(other);
  }

  /**
   * RecordBuilder for RatedMovie instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RatedMovie>
    implements org.apache.avro.data.RecordBuilder<RatedMovie> {

    private long id;
    private java.lang.CharSequence title;
    private int release_year;
    private double rating;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.godeltech.com.kafkademo.schema.RatedMovie.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.release_year)) {
        this.release_year = data().deepCopy(fields()[2].schema(), other.release_year);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.rating)) {
        this.rating = data().deepCopy(fields()[3].schema(), other.rating);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing RatedMovie instance
     * @param other The existing instance to copy.
     */
    private Builder(com.godeltech.com.kafkademo.schema.RatedMovie other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.release_year)) {
        this.release_year = data().deepCopy(fields()[2].schema(), other.release_year);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.rating)) {
        this.rating = data().deepCopy(fields()[3].schema(), other.rating);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * @param value The value of 'title'.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.title = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'title' field.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder clearTitle() {
      title = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'release_year' field.
      * @return The value.
      */
    public java.lang.Integer getReleaseYear() {
      return release_year;
    }

    /**
      * Sets the value of the 'release_year' field.
      * @param value The value of 'release_year'.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder setReleaseYear(int value) {
      validate(fields()[2], value);
      this.release_year = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'release_year' field has been set.
      * @return True if the 'release_year' field has been set, false otherwise.
      */
    public boolean hasReleaseYear() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'release_year' field.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder clearReleaseYear() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'rating' field.
      * @return The value.
      */
    public java.lang.Double getRating() {
      return rating;
    }

    /**
      * Sets the value of the 'rating' field.
      * @param value The value of 'rating'.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder setRating(double value) {
      validate(fields()[3], value);
      this.rating = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'rating' field has been set.
      * @return True if the 'rating' field has been set, false otherwise.
      */
    public boolean hasRating() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'rating' field.
      * @return This builder.
      */
    public com.godeltech.com.kafkademo.schema.RatedMovie.Builder clearRating() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RatedMovie build() {
      try {
        RatedMovie record = new RatedMovie();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.title = fieldSetFlags()[1] ? this.title : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.release_year = fieldSetFlags()[2] ? this.release_year : (java.lang.Integer) defaultValue(fields()[2]);
        record.rating = fieldSetFlags()[3] ? this.rating : (java.lang.Double) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<RatedMovie>
    WRITER$ = (org.apache.avro.io.DatumWriter<RatedMovie>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<RatedMovie>
    READER$ = (org.apache.avro.io.DatumReader<RatedMovie>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
