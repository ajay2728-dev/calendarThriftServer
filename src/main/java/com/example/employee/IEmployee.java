/**
 * Autogenerated by Thrift Compiler (0.21.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.example.employee;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.21.0)", date = "2025-02-12")
public class IEmployee implements org.apache.thrift.TBase<IEmployee, IEmployee._Fields>, java.io.Serializable, Cloneable, Comparable<IEmployee> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("IEmployee");

  private static final org.apache.thrift.protocol.TField EMPLOYEE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("employeeId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField EMPLOYEE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("employeeName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EMPLOYEE_EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("employeeEmail", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField OFFICE_LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("officeLocation", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField DEPARTMENT_FIELD_DESC = new org.apache.thrift.protocol.TField("department", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField SALARY_FIELD_DESC = new org.apache.thrift.protocol.TField("salary", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField IS_ACTIVE_FIELD_DESC = new org.apache.thrift.protocol.TField("isActive", org.apache.thrift.protocol.TType.BOOL, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new IEmployeeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new IEmployeeTupleSchemeFactory();

  public int employeeId; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String employeeName; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String employeeEmail; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String officeLocation; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String department; // required
  public int salary; // required
  public boolean isActive; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EMPLOYEE_ID((short)1, "employeeId"),
    EMPLOYEE_NAME((short)2, "employeeName"),
    EMPLOYEE_EMAIL((short)3, "employeeEmail"),
    OFFICE_LOCATION((short)4, "officeLocation"),
    DEPARTMENT((short)5, "department"),
    SALARY((short)6, "salary"),
    IS_ACTIVE((short)7, "isActive");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EMPLOYEE_ID
          return EMPLOYEE_ID;
        case 2: // EMPLOYEE_NAME
          return EMPLOYEE_NAME;
        case 3: // EMPLOYEE_EMAIL
          return EMPLOYEE_EMAIL;
        case 4: // OFFICE_LOCATION
          return OFFICE_LOCATION;
        case 5: // DEPARTMENT
          return DEPARTMENT;
        case 6: // SALARY
          return SALARY;
        case 7: // IS_ACTIVE
          return IS_ACTIVE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    @Override
    public short getThriftFieldId() {
      return _thriftId;
    }

    @Override
    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __EMPLOYEEID_ISSET_ID = 0;
  private static final int __SALARY_ISSET_ID = 1;
  private static final int __ISACTIVE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EMPLOYEE_ID, new org.apache.thrift.meta_data.FieldMetaData("employeeId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.EMPLOYEE_NAME, new org.apache.thrift.meta_data.FieldMetaData("employeeName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EMPLOYEE_EMAIL, new org.apache.thrift.meta_data.FieldMetaData("employeeEmail", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OFFICE_LOCATION, new org.apache.thrift.meta_data.FieldMetaData("officeLocation", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DEPARTMENT, new org.apache.thrift.meta_data.FieldMetaData("department", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SALARY, new org.apache.thrift.meta_data.FieldMetaData("salary", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.IS_ACTIVE, new org.apache.thrift.meta_data.FieldMetaData("isActive", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(IEmployee.class, metaDataMap);
  }

  public IEmployee() {
  }

  public IEmployee(
    int employeeId,
    java.lang.String employeeName,
    java.lang.String employeeEmail,
    java.lang.String officeLocation,
    java.lang.String department,
    int salary,
    boolean isActive)
  {
    this();
    this.employeeId = employeeId;
    setEmployeeIdIsSet(true);
    this.employeeName = employeeName;
    this.employeeEmail = employeeEmail;
    this.officeLocation = officeLocation;
    this.department = department;
    this.salary = salary;
    setSalaryIsSet(true);
    this.isActive = isActive;
    setIsActiveIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public IEmployee(IEmployee other) {
    __isset_bitfield = other.__isset_bitfield;
    this.employeeId = other.employeeId;
    if (other.isSetEmployeeName()) {
      this.employeeName = other.employeeName;
    }
    if (other.isSetEmployeeEmail()) {
      this.employeeEmail = other.employeeEmail;
    }
    if (other.isSetOfficeLocation()) {
      this.officeLocation = other.officeLocation;
    }
    if (other.isSetDepartment()) {
      this.department = other.department;
    }
    this.salary = other.salary;
    this.isActive = other.isActive;
  }

  @Override
  public IEmployee deepCopy() {
    return new IEmployee(this);
  }

  @Override
  public void clear() {
    setEmployeeIdIsSet(false);
    this.employeeId = 0;
    this.employeeName = null;
    this.employeeEmail = null;
    this.officeLocation = null;
    this.department = null;
    setSalaryIsSet(false);
    this.salary = 0;
    setIsActiveIsSet(false);
    this.isActive = false;
  }

  public int getEmployeeId() {
    return this.employeeId;
  }

  public IEmployee setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
    setEmployeeIdIsSet(true);
    return this;
  }

  public void unsetEmployeeId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EMPLOYEEID_ISSET_ID);
  }

  /** Returns true if field employeeId is set (has been assigned a value) and false otherwise */
  public boolean isSetEmployeeId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EMPLOYEEID_ISSET_ID);
  }

  public void setEmployeeIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __EMPLOYEEID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getEmployeeName() {
    return this.employeeName;
  }

  public IEmployee setEmployeeName(@org.apache.thrift.annotation.Nullable java.lang.String employeeName) {
    this.employeeName = employeeName;
    return this;
  }

  public void unsetEmployeeName() {
    this.employeeName = null;
  }

  /** Returns true if field employeeName is set (has been assigned a value) and false otherwise */
  public boolean isSetEmployeeName() {
    return this.employeeName != null;
  }

  public void setEmployeeNameIsSet(boolean value) {
    if (!value) {
      this.employeeName = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getEmployeeEmail() {
    return this.employeeEmail;
  }

  public IEmployee setEmployeeEmail(@org.apache.thrift.annotation.Nullable java.lang.String employeeEmail) {
    this.employeeEmail = employeeEmail;
    return this;
  }

  public void unsetEmployeeEmail() {
    this.employeeEmail = null;
  }

  /** Returns true if field employeeEmail is set (has been assigned a value) and false otherwise */
  public boolean isSetEmployeeEmail() {
    return this.employeeEmail != null;
  }

  public void setEmployeeEmailIsSet(boolean value) {
    if (!value) {
      this.employeeEmail = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getOfficeLocation() {
    return this.officeLocation;
  }

  public IEmployee setOfficeLocation(@org.apache.thrift.annotation.Nullable java.lang.String officeLocation) {
    this.officeLocation = officeLocation;
    return this;
  }

  public void unsetOfficeLocation() {
    this.officeLocation = null;
  }

  /** Returns true if field officeLocation is set (has been assigned a value) and false otherwise */
  public boolean isSetOfficeLocation() {
    return this.officeLocation != null;
  }

  public void setOfficeLocationIsSet(boolean value) {
    if (!value) {
      this.officeLocation = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDepartment() {
    return this.department;
  }

  public IEmployee setDepartment(@org.apache.thrift.annotation.Nullable java.lang.String department) {
    this.department = department;
    return this;
  }

  public void unsetDepartment() {
    this.department = null;
  }

  /** Returns true if field department is set (has been assigned a value) and false otherwise */
  public boolean isSetDepartment() {
    return this.department != null;
  }

  public void setDepartmentIsSet(boolean value) {
    if (!value) {
      this.department = null;
    }
  }

  public int getSalary() {
    return this.salary;
  }

  public IEmployee setSalary(int salary) {
    this.salary = salary;
    setSalaryIsSet(true);
    return this;
  }

  public void unsetSalary() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SALARY_ISSET_ID);
  }

  /** Returns true if field salary is set (has been assigned a value) and false otherwise */
  public boolean isSetSalary() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SALARY_ISSET_ID);
  }

  public void setSalaryIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SALARY_ISSET_ID, value);
  }

  public boolean isIsActive() {
    return this.isActive;
  }

  public IEmployee setIsActive(boolean isActive) {
    this.isActive = isActive;
    setIsActiveIsSet(true);
    return this;
  }

  public void unsetIsActive() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISACTIVE_ISSET_ID);
  }

  /** Returns true if field isActive is set (has been assigned a value) and false otherwise */
  public boolean isSetIsActive() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISACTIVE_ISSET_ID);
  }

  public void setIsActiveIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISACTIVE_ISSET_ID, value);
  }

  @Override
  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case EMPLOYEE_ID:
      if (value == null) {
        unsetEmployeeId();
      } else {
        setEmployeeId((java.lang.Integer)value);
      }
      break;

    case EMPLOYEE_NAME:
      if (value == null) {
        unsetEmployeeName();
      } else {
        setEmployeeName((java.lang.String)value);
      }
      break;

    case EMPLOYEE_EMAIL:
      if (value == null) {
        unsetEmployeeEmail();
      } else {
        setEmployeeEmail((java.lang.String)value);
      }
      break;

    case OFFICE_LOCATION:
      if (value == null) {
        unsetOfficeLocation();
      } else {
        setOfficeLocation((java.lang.String)value);
      }
      break;

    case DEPARTMENT:
      if (value == null) {
        unsetDepartment();
      } else {
        setDepartment((java.lang.String)value);
      }
      break;

    case SALARY:
      if (value == null) {
        unsetSalary();
      } else {
        setSalary((java.lang.Integer)value);
      }
      break;

    case IS_ACTIVE:
      if (value == null) {
        unsetIsActive();
      } else {
        setIsActive((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case EMPLOYEE_ID:
      return getEmployeeId();

    case EMPLOYEE_NAME:
      return getEmployeeName();

    case EMPLOYEE_EMAIL:
      return getEmployeeEmail();

    case OFFICE_LOCATION:
      return getOfficeLocation();

    case DEPARTMENT:
      return getDepartment();

    case SALARY:
      return getSalary();

    case IS_ACTIVE:
      return isIsActive();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  @Override
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case EMPLOYEE_ID:
      return isSetEmployeeId();
    case EMPLOYEE_NAME:
      return isSetEmployeeName();
    case EMPLOYEE_EMAIL:
      return isSetEmployeeEmail();
    case OFFICE_LOCATION:
      return isSetOfficeLocation();
    case DEPARTMENT:
      return isSetDepartment();
    case SALARY:
      return isSetSalary();
    case IS_ACTIVE:
      return isSetIsActive();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof IEmployee)
      return this.equals((IEmployee)that);
    return false;
  }

  public boolean equals(IEmployee that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_employeeId = true;
    boolean that_present_employeeId = true;
    if (this_present_employeeId || that_present_employeeId) {
      if (!(this_present_employeeId && that_present_employeeId))
        return false;
      if (this.employeeId != that.employeeId)
        return false;
    }

    boolean this_present_employeeName = true && this.isSetEmployeeName();
    boolean that_present_employeeName = true && that.isSetEmployeeName();
    if (this_present_employeeName || that_present_employeeName) {
      if (!(this_present_employeeName && that_present_employeeName))
        return false;
      if (!this.employeeName.equals(that.employeeName))
        return false;
    }

    boolean this_present_employeeEmail = true && this.isSetEmployeeEmail();
    boolean that_present_employeeEmail = true && that.isSetEmployeeEmail();
    if (this_present_employeeEmail || that_present_employeeEmail) {
      if (!(this_present_employeeEmail && that_present_employeeEmail))
        return false;
      if (!this.employeeEmail.equals(that.employeeEmail))
        return false;
    }

    boolean this_present_officeLocation = true && this.isSetOfficeLocation();
    boolean that_present_officeLocation = true && that.isSetOfficeLocation();
    if (this_present_officeLocation || that_present_officeLocation) {
      if (!(this_present_officeLocation && that_present_officeLocation))
        return false;
      if (!this.officeLocation.equals(that.officeLocation))
        return false;
    }

    boolean this_present_department = true && this.isSetDepartment();
    boolean that_present_department = true && that.isSetDepartment();
    if (this_present_department || that_present_department) {
      if (!(this_present_department && that_present_department))
        return false;
      if (!this.department.equals(that.department))
        return false;
    }

    boolean this_present_salary = true;
    boolean that_present_salary = true;
    if (this_present_salary || that_present_salary) {
      if (!(this_present_salary && that_present_salary))
        return false;
      if (this.salary != that.salary)
        return false;
    }

    boolean this_present_isActive = true;
    boolean that_present_isActive = true;
    if (this_present_isActive || that_present_isActive) {
      if (!(this_present_isActive && that_present_isActive))
        return false;
      if (this.isActive != that.isActive)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + employeeId;

    hashCode = hashCode * 8191 + ((isSetEmployeeName()) ? 131071 : 524287);
    if (isSetEmployeeName())
      hashCode = hashCode * 8191 + employeeName.hashCode();

    hashCode = hashCode * 8191 + ((isSetEmployeeEmail()) ? 131071 : 524287);
    if (isSetEmployeeEmail())
      hashCode = hashCode * 8191 + employeeEmail.hashCode();

    hashCode = hashCode * 8191 + ((isSetOfficeLocation()) ? 131071 : 524287);
    if (isSetOfficeLocation())
      hashCode = hashCode * 8191 + officeLocation.hashCode();

    hashCode = hashCode * 8191 + ((isSetDepartment()) ? 131071 : 524287);
    if (isSetDepartment())
      hashCode = hashCode * 8191 + department.hashCode();

    hashCode = hashCode * 8191 + salary;

    hashCode = hashCode * 8191 + ((isActive) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(IEmployee other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetEmployeeId(), other.isSetEmployeeId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmployeeId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.employeeId, other.employeeId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetEmployeeName(), other.isSetEmployeeName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmployeeName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.employeeName, other.employeeName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetEmployeeEmail(), other.isSetEmployeeEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmployeeEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.employeeEmail, other.employeeEmail);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetOfficeLocation(), other.isSetOfficeLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOfficeLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.officeLocation, other.officeLocation);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetDepartment(), other.isSetDepartment());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDepartment()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.department, other.department);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetSalary(), other.isSetSalary());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSalary()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.salary, other.salary);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetIsActive(), other.isSetIsActive());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsActive()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isActive, other.isActive);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  @Override
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  @Override
  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  @Override
  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("IEmployee(");
    boolean first = true;

    sb.append("employeeId:");
    sb.append(this.employeeId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("employeeName:");
    if (this.employeeName == null) {
      sb.append("null");
    } else {
      sb.append(this.employeeName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("employeeEmail:");
    if (this.employeeEmail == null) {
      sb.append("null");
    } else {
      sb.append(this.employeeEmail);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("officeLocation:");
    if (this.officeLocation == null) {
      sb.append("null");
    } else {
      sb.append(this.officeLocation);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("department:");
    if (this.department == null) {
      sb.append("null");
    } else {
      sb.append(this.department);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("salary:");
    sb.append(this.salary);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isActive:");
    sb.append(this.isActive);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class IEmployeeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public IEmployeeStandardScheme getScheme() {
      return new IEmployeeStandardScheme();
    }
  }

  private static class IEmployeeStandardScheme extends org.apache.thrift.scheme.StandardScheme<IEmployee> {

    @Override
    public void read(org.apache.thrift.protocol.TProtocol iprot, IEmployee struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EMPLOYEE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.employeeId = iprot.readI32();
              struct.setEmployeeIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // EMPLOYEE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.employeeName = iprot.readString();
              struct.setEmployeeNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EMPLOYEE_EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.employeeEmail = iprot.readString();
              struct.setEmployeeEmailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // OFFICE_LOCATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.officeLocation = iprot.readString();
              struct.setOfficeLocationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DEPARTMENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.department = iprot.readString();
              struct.setDepartmentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SALARY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.salary = iprot.readI32();
              struct.setSalaryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // IS_ACTIVE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isActive = iprot.readBool();
              struct.setIsActiveIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    @Override
    public void write(org.apache.thrift.protocol.TProtocol oprot, IEmployee struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(EMPLOYEE_ID_FIELD_DESC);
      oprot.writeI32(struct.employeeId);
      oprot.writeFieldEnd();
      if (struct.employeeName != null) {
        oprot.writeFieldBegin(EMPLOYEE_NAME_FIELD_DESC);
        oprot.writeString(struct.employeeName);
        oprot.writeFieldEnd();
      }
      if (struct.employeeEmail != null) {
        oprot.writeFieldBegin(EMPLOYEE_EMAIL_FIELD_DESC);
        oprot.writeString(struct.employeeEmail);
        oprot.writeFieldEnd();
      }
      if (struct.officeLocation != null) {
        oprot.writeFieldBegin(OFFICE_LOCATION_FIELD_DESC);
        oprot.writeString(struct.officeLocation);
        oprot.writeFieldEnd();
      }
      if (struct.department != null) {
        oprot.writeFieldBegin(DEPARTMENT_FIELD_DESC);
        oprot.writeString(struct.department);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SALARY_FIELD_DESC);
      oprot.writeI32(struct.salary);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(IS_ACTIVE_FIELD_DESC);
      oprot.writeBool(struct.isActive);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class IEmployeeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    @Override
    public IEmployeeTupleScheme getScheme() {
      return new IEmployeeTupleScheme();
    }
  }

  private static class IEmployeeTupleScheme extends org.apache.thrift.scheme.TupleScheme<IEmployee> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, IEmployee struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetEmployeeId()) {
        optionals.set(0);
      }
      if (struct.isSetEmployeeName()) {
        optionals.set(1);
      }
      if (struct.isSetEmployeeEmail()) {
        optionals.set(2);
      }
      if (struct.isSetOfficeLocation()) {
        optionals.set(3);
      }
      if (struct.isSetDepartment()) {
        optionals.set(4);
      }
      if (struct.isSetSalary()) {
        optionals.set(5);
      }
      if (struct.isSetIsActive()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetEmployeeId()) {
        oprot.writeI32(struct.employeeId);
      }
      if (struct.isSetEmployeeName()) {
        oprot.writeString(struct.employeeName);
      }
      if (struct.isSetEmployeeEmail()) {
        oprot.writeString(struct.employeeEmail);
      }
      if (struct.isSetOfficeLocation()) {
        oprot.writeString(struct.officeLocation);
      }
      if (struct.isSetDepartment()) {
        oprot.writeString(struct.department);
      }
      if (struct.isSetSalary()) {
        oprot.writeI32(struct.salary);
      }
      if (struct.isSetIsActive()) {
        oprot.writeBool(struct.isActive);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, IEmployee struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.employeeId = iprot.readI32();
        struct.setEmployeeIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.employeeName = iprot.readString();
        struct.setEmployeeNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.employeeEmail = iprot.readString();
        struct.setEmployeeEmailIsSet(true);
      }
      if (incoming.get(3)) {
        struct.officeLocation = iprot.readString();
        struct.setOfficeLocationIsSet(true);
      }
      if (incoming.get(4)) {
        struct.department = iprot.readString();
        struct.setDepartmentIsSet(true);
      }
      if (incoming.get(5)) {
        struct.salary = iprot.readI32();
        struct.setSalaryIsSet(true);
      }
      if (incoming.get(6)) {
        struct.isActive = iprot.readBool();
        struct.setIsActiveIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

