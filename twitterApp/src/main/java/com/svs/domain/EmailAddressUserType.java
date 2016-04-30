package com.svs.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

public class EmailAddressUserType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Class returnedClass() {
		return EmailAddress.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		if (x != null) {
			return x.hashCode();
		} else {
			return 0;
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		EmailAddress email = null;

		String name = rs.getString(names[0]);
		if (name != null) {
			email = new EmailAddress();

			StringTokenizer stk = new StringTokenizer(name, "-");
			email.setLocalPart(stk.nextToken());
			email.setDomain(stk.nextToken());
		}
		return email;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {

		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			st.setString(index, ((EmailAddress) value).toString());
		}

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		} else {
			EmailAddress newEA = new EmailAddress();
			EmailAddress existingEA = (EmailAddress) value;

			newEA.setLocalPart(existingEA.getLocalPart());
			newEA.setDomain(existingEA.getDomain());

			return newEA;
		}
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		Object deepCopy = deepCopy(value);

		if (!(deepCopy instanceof Serializable))
			return (Serializable) deepCopy;
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

}
