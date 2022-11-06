// package mapper.mpconfig;
//
// import com.baomidou.mybatisplus.core.mapper.BaseMapper;
// import com.xuegao.mapper.mpservice.AbstractMpServiceV2;
// import mapper.mapper.SysUserMapper;
// import mapper.model.SysUser;
// import org.junit.Test;
// import org.springframework.boot.test.context.SpringBootTest;
//
// @SpringBootTest
// public class disable extends AbstractMpServiceV2<SysUserMapper, SysUser, Integer> {
//
//     protected disable(BaseMapper<SysUser> baseMapper) {
//         super(baseMapper);
//     }
//
//     @Override
//     public String shardingKey() {
//         return null;
//     }
//
//     @Test
//     public void testDisable() {
//         disable(1);
//     }
// }
