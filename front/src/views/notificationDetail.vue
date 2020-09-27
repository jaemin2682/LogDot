<template>
  <div class="container-base">
    <div class="notification-list">
      <div class="row">
        <span id="all">내 알림</span>
        <router-link id="setting" to="/setting/alarm">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 0 26 26"
          >
            <path
              d="M22.2,14.2c0.1-0.4,0.1-0.8,0.1-1.2c0-0.4,0-0.8-0.1-1.2l2.6-2C25,9.6,25.1,9.2,24.9,9l-2.5-4.3c-0.2-0.3-0.5-0.4-0.8-0.3
          l-3.1,1.2c-0.6-0.5-1.3-0.9-2.1-1.2l-0.5-3.3c-0.1-0.3-0.3-0.5-0.6-0.5h-4.9c-0.3,0-0.6,0.2-0.6,0.5L9.5,4.5C8.7,4.8,8,5.2,7.4,5.7
          L4.3,4.4C4,4.3,3.7,4.4,3.6,4.7L1.1,9C0.9,9.2,1,9.6,1.3,9.8l2.6,2c-0.1,0.4-0.1,0.8-0.1,1.2s0,0.8,0.1,1.2l-2.6,2
          C1,16.4,0.9,16.8,1.1,17l2.5,4.3c0.2,0.3,0.5,0.4,0.8,0.3l3.1-1.2c0.6,0.5,1.3,0.9,2.1,1.2l0.5,3.3c0.1,0.3,0.3,0.5,0.6,0.5h4.9
          c0.3,0,0.6-0.2,0.6-0.5l0.5-3.3c0.8-0.3,1.5-0.7,2.1-1.2l3.1,1.2c0.3,0.1,0.6,0,0.8-0.3l2.5-4.3c0.2-0.3,0.1-0.6-0.2-0.8L22.2,14.2z
          M13,17.6c-2.5,0-4.6-2.1-4.6-4.6s2.1-4.6,4.6-4.6s4.6,2.1,4.6,4.6S15.5,17.6,13,17.6z"
            />
          </svg>
        </router-link>
      </div>
      <hr />
      <div class="notification-delete">
        <button @click="deleteread">읽은 알림 삭제</button>
        <span>|</span>
        <button @click="deleteall">전체 삭제</button>
      </div>
      <div class="default" v-if="notifications.length == 0">
        <svg
          version="1.1"
          id="Capa_1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          viewBox="0 0 416 416"
          style="enable-background:new 0 0 416 416;"
          xml:space="preserve"
        >
          <g>
            <g>
              <g>
                <path
                  d="M208,416c23.573,0,42.667-19.093,42.667-42.667h-85.333C165.333,396.907,184.427,416,208,416z"
                />
                <path
                  d="M336,288V181.333c0-65.6-34.88-120.32-96-134.827V32c0-17.707-14.293-32-32-32s-32,14.293-32,32v14.507
                              c-61.12,14.507-96,69.227-96,134.827V288l-42.667,42.667V352h341.333v-21.333L336,288z"
                />
              </g>
            </g>
          </g>
        </svg>
        <h4>이곳에 전체 알림이 표시됩니다.</h4>
        <span>블로그 활동을 통해 알림을 받아보세요.</span>
      </div>
      <template v-for="(data, id) in notifications">
        <SubNotification :key="id" v-bind:data="data" />
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import SubNotification from "@/components/notification/SubNotificationDetail.vue";

const SERVER_URL = "http://i3a604.p.ssafy.io:8081";

export default {
  name: "notification",
  components: { SubNotification },
  data() {
    return {
      notifications: []
    };
  },
  methods: {
    ...mapGetters({
      getEmail: "user/getEmail"
    }),
    deleteall() {
      axios
        .delete(`${SERVER_URL}/api/notification/all`, {
          params: {
            email: this.getEmail()
          }
        })
        .then(() => {
          location.reload();
        })
        .catch(err => {
          console.log(err);
        });
    },
    deleteread() {
      axios
        .delete(`${SERVER_URL}/api/notification/read`, {
          params: {
            email: this.getEmail()
          }
        })
        .then(() => {
          location.reload();
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  created() {
    axios
      .get(`${SERVER_URL}/api/notification/all`, {
        params: {
          email: this.getEmail()
        }
      })
      .then(res => {
        this.notifications = res.data;
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>

<style scoped lang="scss">
.container-base {
  padding-bottom: 20px;
}
.row {
  width: 100%;
  display: inline-block;
  padding: 16px;
  #all {
    float: left;
    font-size: 1.2rem;
    font-weight: bold;
    color: #727272;
  }
  #setting {
    width: 20px;
    margin-top: 2px;
    float: right;
    fill: #c0c0c0;
    cursor: pointer;
  }
}
.notification-list {
  width: 60%;
  margin: auto;
  overflow-y: auto;
  background-color: #fafafa;
  min-height: 400px;

  .default {
    color: #727272;
    margin: 50px 0;
    text-align: center;
    svg {
      width: 100px;
      fill: #c0c0c0;
      margin: 15px 0;
    }
    h4 {
      margin: 8px 0;
    }
    span {
      font-size: 14px;
    }
  }
}
.notification-list::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
hr {
  margin: 3px 0;
  border-left: thick;
  border-top: 0px;
}

.notification-delete {
  width: 100%;
  margin-bottom: 20px;
  text-align: right;
  button {
    color: #727272;
  }
  span {
    color: #727272;
    margin: 0px 4px;
    font-size: 14px;
  }
}
@import "@/assets/common/Base.scss";
</style>
