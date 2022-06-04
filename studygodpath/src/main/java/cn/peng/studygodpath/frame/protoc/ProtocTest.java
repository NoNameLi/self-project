package cn.peng.studygodpath.frame.protoc;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.TextFormat;
import org.junit.Test;
import org.testng.Assert;

public class ProtocTest {

    @Test
    public void test() throws InvalidProtocolBufferException {
        PersonTestProtos.Person.PhoneNumber phoneNumber = PersonTestProtos.Person.PhoneNumber.newBuilder().setType(PersonTestProtos.Person.PhoneNumber.PhoneType.HOME).setNumber("15572702153").build();

        PersonTestProtos.Person person = PersonTestProtos.Person.newBuilder().setId(1).setName("Mrs.李")
                .setEmail("y159632957@qq.com").setSex(PersonTestProtos.Person.Sex.MALE)./*addPhone(phoneNumber).*/putTags("感情", "不成熟").build();
        System.out.println(person.getPhoneList());
        ByteString bytes = person.toByteString();
        PersonTestProtos.Person parseFrom = PersonTestProtos.Person.parseFrom(bytes);

        System.out.printf(TextFormat.printer().escapingNonAscii(false).printToString(parseFrom));
        Assert.assertEquals("Mrs.李", parseFrom.getName());

    }

}
